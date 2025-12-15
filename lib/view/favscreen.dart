import 'package:flutter/material.dart';
import 'package:movie/view/product_screen.dart';
import '../model/product.dart';
import 'product_card.dart';

class FavoritesScreen extends StatelessWidget {
  final List<Movie> favorites;

  const FavoritesScreen({super.key, required this.favorites});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.black,
        title: const Text('Favorites', style: TextStyle(color: Colors.white)),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back, color: Colors.white),
          onPressed: () {
            Navigator.pop(MovieScreen() as BuildContext); 
          },
        ),
      ),
      backgroundColor: Colors.black,
      body: favorites.isEmpty
          ? const Center(
              child: Text('No favorites yet', style: TextStyle(color: Colors.white)),
            )
          : GridView.builder(
              padding: const EdgeInsets.all(12),
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                crossAxisSpacing: 12,
                mainAxisSpacing: 12,
                childAspectRatio: 0.62,
              ),
              itemCount: favorites.length,
              itemBuilder: (context, index) {
                final movie = favorites[index];
                return MovieGridItem(
                  movie: movie,
                  onFavoriteChanged: () {
                   
                    (context as Element).markNeedsBuild();
                  },
                );
              },
            ),
    );
  }
}
