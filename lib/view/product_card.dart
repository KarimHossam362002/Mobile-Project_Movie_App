import 'package:flutter/material.dart';
import 'package:movie/model/product.dart';
import 'package:movie/view/movie_detail.dart';

class MovieCard extends StatelessWidget {
  final Movie movie;

  const MovieCard({super.key, required this.movie});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) {
              return MovieDetail(movie: movie);
            },
          ),
        );
      },
      child: Card(
        margin: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
        elevation: 4,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
        child: Container(
          height: 180,
          padding: const EdgeInsets.all(8),
          child: Row(
            children: [
              // Poster
              ClipRRect(
                borderRadius: BorderRadius.circular(8),
                child: movie.poster.isNotEmpty
                    ? Image.network(
                        movie.poster,
                        width: 120,
                        height: double.infinity,
                        fit: BoxFit.cover,
                        errorBuilder: (context, error, stackTrace) {
                          return Container(
                            width: 120,
                            height: double.infinity,
                            color: Colors.grey[300],
                            child: const Icon(Icons.movie, size: 50),
                          );
                        },
                      )
                    : Container(
                        width: 120,
                        height: double.infinity,
                        color: Colors.grey[300],
                        child: const Icon(Icons.movie, size: 50),
                      ),
              ),
              const SizedBox(width: 12),

              // Title & Year
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(
                      movie.title,
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      'Year: ${movie.year}',
                      style: TextStyle(fontSize: 16, color: Colors.grey[700]),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
