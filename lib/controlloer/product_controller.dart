
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../model/product.dart';

class MovieController {
  final String apiKey = '361435e9';  

  Future<List<Movie>> searchMovies(String query) async {
    final url = Uri.parse('http://www.omdbapi.com/?s=${Uri.encodeComponent(query)}&apikey=$apiKey');

    final response = await http.get(url);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      if (data['Response'] == 'True') {
        final List movies = data['Search'];
        return movies.map((m) => Movie.fromJson(m)).toList();
      } else if (data['Error'] == 'Too many results.') {
        throw Exception('Too many results, please refine your search');
      } else {
        throw Exception(data['Error'] ?? 'No movies found');
      }
    } else {
      throw Exception('Failed to fetch movies');
    }
  }
}