import 'package:flutter/material.dart';
import '../model/product.dart';
import 'product_screen.dart'; 

class MovieDetail extends StatelessWidget {
  final Movie movie;

  const MovieDetail({super.key, required this.movie});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        backgroundColor: Colors.black,
        elevation: 0,
        iconTheme: const IconThemeData(color: Colors.white),
        title: const Text(
          'Movie',
          style: TextStyle(color: Colors.white),
        ),
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [

            Container(
              width: double.infinity,
              height: 360,
              decoration: BoxDecoration(
                border: BoxBorder.all(color: Colors.white),
                borderRadius: const BorderRadius.only(
                  bottomLeft: Radius.circular(45),
                  bottomRight: Radius.circular(45),
                ),
                color: Colors.white,
              ),
              clipBehavior: Clip.antiAlias, 
              child: movie.poster.isNotEmpty
                  ? Image.network(
                      movie.poster,
                      fit: BoxFit.cover,
                      errorBuilder: (_, __, ___) {
                        return _fallbackImage();
                      },
                    )
                  : _fallbackImage(),
            ),

            const SizedBox(height: 20),

            Row(
              children: [
                const SizedBox(width: 16),
                const Text(
                  "Film Name:",
                  style: TextStyle(fontSize: 17, fontWeight: FontWeight.bold, color: Colors.white),
                ),
                const SizedBox(width: 5),
                Text(
                  movie.title,
                  style: const TextStyle(fontSize: 20, color: Colors.white70),
                ),
              ],
            ),

            const SizedBox(height: 8),

        
            Row(
              children: [
                const SizedBox(width: 16),
                const Text(
                  "Year: ",
                  style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold, color: Colors.white),
                ),
                const SizedBox(width: 5),
                Text(
                  movie.year,
                  style: const TextStyle(fontSize: 16, color: Colors.white70),
                ),
              ],
            ),

            const SizedBox(height: 16),

           
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: const [
                  Text(
                    "Description",
                    style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold, color: Colors.white),
                  ),
                  SizedBox(height: 8),
                  Text(
                    "A popular movie that gained wide recognition and attracted a large audience worldwide.",
                    style: TextStyle(fontSize: 15, color: Colors.white60, height: 1.4),
                  ),
                ],
              ),
            ),

            const SizedBox(height: 30),

            Center(
              child: ElevatedButton.icon(
                onPressed: () {
                  if (!favorites.contains(movie)) {
                    favorites.add(movie);
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                        content: Text("${movie.title} added to favorites!",style: TextStyle(color: Colors.black),),
                        backgroundColor: Colors.white,
                        duration: const Duration(seconds: 2),
                      ),
                    );
                  } else {
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                        content: Text("${movie.title} is already in favorites!",style: TextStyle(color: Colors.white)),
                        backgroundColor: Colors.grey[800],
                        duration: const Duration(seconds: 2),
                      ),
                    );
                  }
                },
                icon: const Icon(Icons.favorite, color: Colors.red),
                label: const Text("Add to Favorites", style: TextStyle(color: Colors.white)),
                style: ElevatedButton.styleFrom(
                  shadowColor: Colors.white,
                  backgroundColor: Colors.black,
                  padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(30)),
                ),
              ),
            ),

            const SizedBox(height: 30),
          ],
        ),
      ),
    );
  }
}


Widget _fallbackImage() {
  return Container(
    width: double.infinity,
    color: Colors.black26,
    child: Center(
      child: Image.asset(
        '.dart_tool/Images/hqdefault.jpg',
         fit: BoxFit.cover,
        opacity: const AlwaysStoppedAnimation(0.7),
      ),
    ),
  );
}
