import 'package:flutter/material.dart';
import 'package:movie/model/product.dart';
import 'package:movie/view/movie_detail.dart';

import 'product_screen.dart';
class MovieGridItem extends StatefulWidget {
  final Movie movie;
  final VoidCallback? onFavoriteChanged; 

  const MovieGridItem({super.key, required this.movie, this.onFavoriteChanged});

  @override
  State<MovieGridItem> createState() => _MovieGridItemState();
}

class _MovieGridItemState extends State<MovieGridItem> {
  bool isFavorite = false;

  @override
  void initState() {
    super.initState();
    isFavorite = favorites.contains(widget.movie);
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        GestureDetector(
          onTap: () {
              Navigator.push(
              context,
              MaterialPageRoute(
                builder: (_) => MovieDetail(movie: widget.movie),
              ),
              );
          },
          child: Card(
            color: Colors.black,
            child: Column(
              children: [
               Expanded(
                child: (widget.movie.poster.isNotEmpty)
                    ? Image.network(
                        widget.movie.poster,
                        fit: BoxFit.cover,
                        width: double.infinity,
                        errorBuilder: (context, error, stackTrace) {
                        
                          return _fallbackImage();
                        },
                      )
                    : _fallbackImage(), 
              ),

                Padding(
                  padding: const EdgeInsets.all(8),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(widget.movie.title,
                          maxLines: 2,
                          overflow: TextOverflow.ellipsis,
                          style: const TextStyle(
                              color: Colors.white, fontWeight: FontWeight.bold)),
                      Text('Year: ${widget.movie.year}',
                          style: const TextStyle(color: Colors.grey)),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
        Positioned(
          top: 8,
          right: 8,
          child: 
           IconButton(
  icon: Icon(
    isFavorite ? Icons.favorite : Icons.favorite_border,
    color: isFavorite ? Colors.red : Colors.white,
  ),
  onPressed: () {
    setState(() {
      isFavorite = !isFavorite;
      if (isFavorite) {
        favorites.add(widget.movie);
      } else {
        favorites.remove(widget.movie);
      }
      if (widget.onFavoriteChanged != null) {
        widget.onFavoriteChanged!();
      }
    });
  },
          ),
        ),
      ],
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

