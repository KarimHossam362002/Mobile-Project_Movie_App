import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../auth/welcome.dart';
import '../bloc/product_bloc.dart';
import '../bloc/product_event.dart';
import '../bloc/product_state.dart';
import '../controlloer/product_controller.dart';
import 'favscreen.dart';
import '../model/product.dart';
import 'product_card.dart';

List<Movie> favorites = [];

class MovieScreen extends StatefulWidget {
  const MovieScreen({super.key});

  @override
  State<MovieScreen> createState() => _MovieScreenState();
}

class _MovieScreenState extends State<MovieScreen> {
  final MovieController controller = MovieController();
  final TextEditingController searchController = TextEditingController();
  late final MovieBloc movieBloc;

  List<Movie> allMovies = []; 
  int _selectedIndex = 0;

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

  void _onNavTap(int index) {
  
    if (index == 2) {
      
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => const WelcomeScreen()),
      );
    } 
    setState(() {
      _selectedIndex = index;
    });
  }

  void _onSearchChanged(String query) {
    if (query.isEmpty) {
   
      movieBloc.add(SearchMovies(""));
    } else {
      movieBloc.add(SearchMovies(query));
    }
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> _pages = [
      _MovieGridPage(
        movieBloc: movieBloc,
        searchController: searchController,
        onSearchChanged: _onSearchChanged,
      ),
      FavoritesScreen(favorites: favorites),
      SizedBox(),
    ];

    return BlocProvider.value(
      value: movieBloc,
      child: Scaffold(
        backgroundColor: Colors.black,
        body: _pages[_selectedIndex],
        bottomNavigationBar: BottomNavigationBar(
          backgroundColor: Colors.black,
          selectedItemColor: Colors.red,
          unselectedItemColor: Colors.white,
          currentIndex: _selectedIndex,
          onTap: _onNavTap,
          items: const [
            
            BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Home'),
            BottomNavigationBarItem(icon: Icon(Icons.favorite), label: 'Favorites'),
             BottomNavigationBarItem(icon: Icon(Icons.logout), label: 'Logout'),
          ],
        ),
      ),
    );
  }
}

class _MovieGridPage extends StatelessWidget {
  final MovieBloc movieBloc;
  final TextEditingController searchController;
  final Function(String) onSearchChanged;

  const _MovieGridPage({
    required this.movieBloc,
    required this.searchController,
    required this.onSearchChanged,
  });

 @override
Widget build(BuildContext context) {
  return BlocBuilder<MovieBloc, MovieState>(
    builder: (context, state) {
      return SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
SizedBox(height: 28,),
            /// 🔍 Search
            Padding(
              padding: const EdgeInsets.fromLTRB(12, 12, 12, 6),
              child: Card(
                color: Colors.white10,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12)),
                child: TextField(
                  controller: searchController,
                  onChanged: onSearchChanged,
                  style: const TextStyle(color: Colors.white),
                  decoration: const InputDecoration(
                    hintText: 'Search movies...',
                    hintStyle: TextStyle(color: Colors.white54),
                    prefixIcon: Icon(Icons.search, color: Colors.white),
                    border: InputBorder.none,
                  ),
                ),
              ),
            ),
SizedBox(height: 5,),
          
            SizedBox(
              height: 33,
              child: ListView(
                scrollDirection: Axis.horizontal,
                padding: const EdgeInsets.symmetric(horizontal: 12),
                children: [
                  _categoryChip('ALL'),
                  _categoryChip('Action'),
                  _categoryChip('Drama'),
                  _categoryChip('Comedy'),
                  _categoryChip('Romance'),
                 
                ],
              ),
            ),

            const SizedBox(height: 17),

           
            if (state is MovieLoaded)
             SizedBox(
                      height: 160,
                      child: Stack(
                        children: [

                      
                          ListView.builder(
                            scrollDirection: Axis.horizontal,
                            padding: const EdgeInsets.symmetric(horizontal: 12),
                            itemCount: state.movies.length,
                            itemBuilder: (context, index) {
                              final movie = state.movies[index];
                              return Padding(
                                padding: const EdgeInsets.only(right: 8),
                                child:ClipRRect(
                              borderRadius: BorderRadius.circular(12),
                              child: SizedBox(
                                width: 250,
                                child: movie.poster.isNotEmpty
                                    ? Image.network(
                                        movie.poster,
                                        fit: BoxFit.cover,
                                        errorBuilder: (context, error, stackTrace) {
                                          return _horizontalFallbackImage();
                                        },
                                      )
                                    : _horizontalFallbackImage(),
                              ),
                            ),

                              );
                            },
                          ),

                     
                         Positioned(
                            bottom: 10,
                            left: 16,
                            child: Container(
                              child: const Text(
                                'New',
                                style: TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 25,
                                  letterSpacing: 1,
                                ),
                              ),
                            ),
                          ),

                        ],
                      ),
                    ),

            const SizedBox(height: 15),

          
            const Padding(
              padding: EdgeInsets.symmetric(horizontal: 10),
              child: Text(
                'Movies',
                style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
            ),

          

            if (state is MovieLoaded)
              GridView.builder(
                shrinkWrap: true,
                physics: const NeverScrollableScrollPhysics(),
                padding: const EdgeInsets.all(12),
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount:
                      MediaQuery.of(context).size.width > 700 ? 3 : 2,
                  crossAxisSpacing: 12,
                  mainAxisSpacing: 12,
                  childAspectRatio: 0.62,
                ),
                itemCount: state.movies.length,
                itemBuilder: (context, index) {
                  return MovieGridItem(movie: state.movies[index]);
                },
              ),
          ],
        ),
      );
    },
  );
}

}
class _EmptyState extends StatelessWidget {
  const _EmptyState({super.key});
  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.movie_filter, size: 80, color: Colors.white),
          SizedBox(height: 12),
          Text(
            'No movies found',
            style: TextStyle(fontSize: 18, color: Colors.white),
          ),
        ],
      ),
    );
  }
}

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
            style: const TextStyle(fontSize: 16, color: Colors.white),
          ),
        ],
      ),
    );
  }
}
Widget _categoryChip(String title) {
  return Container(
    margin: const EdgeInsets.only(right: 8),
    padding: const EdgeInsets.symmetric(horizontal: 16),
    decoration: BoxDecoration(
      color: Colors.white,
      borderRadius: BorderRadius.circular(20),
    ),
    child: Center(
      child: Text(
        title,
        style: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
        ),
      ),
    ),
  );
}
Widget _horizontalFallbackImage() {
  return Image.asset(
    '.dart_tool/Images/hqdefault.jpg',
    fit: BoxFit.cover,
    width: double.infinity,
    height: double.infinity,
  );
}