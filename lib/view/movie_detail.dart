import 'package:flutter/material.dart';
import 'package:movie/model/product.dart';

class MovieDetail extends StatelessWidget {
  final Movie movie;

  const MovieDetail({super.key, required this.movie});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[100],
      appBar: AppBar(
        title: const Text("Movie Detail"),
        centerTitle: true,
        elevation: 0,
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            // Poster with Gradient
            Stack(
              children: [
                SizedBox(
                  width: double.infinity,
                  height: 320,
                  child: movie.poster.isNotEmpty
                      ? Image.network(
                          movie.poster,
                          fit: BoxFit.cover,
                          errorBuilder: (_, __, ___) {
                            return Container(
                              color: Colors.grey[300],
                              child: const Center(
                                child: Icon(Icons.movie, size: 80),
                              ),
                            );
                          },
                        )
                      : Container(
                          color: Colors.grey[300],
                          child: const Center(
                            child: Icon(Icons.movie, size: 80),
                          ),
                        ),
                ),

                // Gradient Overlay
                Container(
                  height: 320,
                  decoration: const BoxDecoration(
                    gradient: LinearGradient(
                      begin: Alignment.bottomCenter,
                      end: Alignment.topCenter,
                      colors: [Colors.black54, Colors.transparent],
                    ),
                  ),
                ),
              ],
            ),

            const SizedBox(height: 16),

            // Info Card
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Card(
                elevation: 4,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(16),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      // Title
                      Text(
                        movie.title,
                        style: const TextStyle(
                          fontSize: 24,
                          fontWeight: FontWeight.bold,
                        ),
                      ),

                      const SizedBox(height: 12),

                      // Year Chip
                      Chip(
                        avatar: const Icon(Icons.calendar_today, size: 18),
                        label: Text(
                          movie.year,
                          style: const TextStyle(fontSize: 16),
                        ),
                      ),

                      const SizedBox(height: 16),

                      // Fake description (اختياري)
                      Text(
                        "This movie is one of the most popular titles of its year. "
                        "It gained wide recognition and attracted a large audience.",
                        style: TextStyle(
                          fontSize: 16,
                          color: Colors.grey[700],
                          height: 1.4,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),

            const SizedBox(height: 20),
          ],
        ),
      ),
    );
  }
}
