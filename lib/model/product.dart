class Movie {
  final String title;
  final String year;
  final String poster;

  Movie({required this.title, required this.year, required this.poster});

  factory Movie.fromJson(Map<String, dynamic> json) {
    final posterFromApi = json['Poster'];

    return Movie(
      title: json['Title'] ?? 'No title',
      year: json['Year'] ?? 'Unknown',
      poster: posterFromApi != null && posterFromApi != 'N/A'
          ? posterFromApi
          : 'https://via.placeholder.com/300x450?text=No+Image',
    );
  }
}
