import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../bloc/product_bloc.dart';
import '../bloc/product_event.dart';
import '../bloc/product_state.dart';
import '../controlloer/product_controller.dart';
import 'product_card.dart';

class MovieScreen extends StatefulWidget {
  const MovieScreen({super.key});

  @override
  State<MovieScreen> createState() => _MovieScreenState();
}

class _MovieScreenState extends State<MovieScreen> {
  final MovieController controller = MovieController();
  final TextEditingController searchController = TextEditingController();
  late final MovieBloc movieBloc;

  @override
  void initState() {
    super.initState();
    movieBloc = MovieBloc(controller);
    movieBloc.add(SearchMovies("Inception"));
  }

  @override
  void dispose() {
    movieBloc.close();
    searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider.value(
      value: movieBloc,
      child: Scaffold(
        backgroundColor: Colors.grey[100],
        appBar: AppBar(
          title: const Text(
            'Movies',
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          centerTitle: true,
          elevation: 0,
        ),
        body: Column(
          children: [
            // 🔍 Search Box
            Padding(
              padding: const EdgeInsets.fromLTRB(12, 12, 12, 6),
              child: Card(
                elevation: 3,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(12),
                ),
                child: TextField(
                  controller: searchController,
                  textInputAction: TextInputAction.search,
                  onSubmitted: (value) {
                    if (value.isNotEmpty) {
                      movieBloc.add(SearchMovies(value));
                    }
                  },
                  decoration: InputDecoration(
                    hintText: 'Search movies...',
                    prefixIcon: const Icon(Icons.search),
                    border: InputBorder.none,
                    contentPadding: const EdgeInsets.all(14),
                  ),
                ),
              ),
            ),

            // 🎬 Movies List
            Expanded(
              child: BlocBuilder<MovieBloc, MovieState>(
                builder: (context, state) {
                  if (state is MovieLoading) {
                    return const Center(child: CircularProgressIndicator());
                  } else if (state is MovieLoaded) {
                    if (state.movies.isEmpty) {
                      return _EmptyState();
                    }
                    return ListView.builder(
                      padding: const EdgeInsets.only(bottom: 12),
                      itemCount: state.movies.length,
                      itemBuilder: (context, index) {
                        final movie = state.movies[index];
                        return MovieCard(movie: movie);
                      },
                    );
                  } else if (state is MovieError) {
                    return _ErrorState(message: state.message);
                  }
                  return const Center(
                    child: Text(
                      'Start searching for your favorite movies 🎬',
                      style: TextStyle(fontSize: 16),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}

/// ------------------
/// Empty State Widget
/// ------------------
class _EmptyState extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.movie_filter, size: 80, color: Colors.grey),
          SizedBox(height: 12),
          Text(
            'No movies found',
            style: TextStyle(fontSize: 18, color: Colors.grey),
          ),
        ],
      ),
    );
  }
}

/// ------------------
/// Error State Widget
/// ------------------
class _ErrorState extends StatelessWidget {
  final String message;

  const _ErrorState({required this.message});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Icon(Icons.error_outline, size: 70, color: Colors.redAccent),
          const SizedBox(height: 12),
          Text(
            message,
            textAlign: TextAlign.center,
            style: const TextStyle(fontSize: 16),
          ),
        ],
      ),
    );
  }
}
