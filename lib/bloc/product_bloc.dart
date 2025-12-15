
import 'package:bloc/bloc.dart';

import '../controlloer/product_controller.dart';
import 'product_event.dart';
import 'product_state.dart';


class MovieBloc extends Bloc<MovieEvent, MovieState> {
  final MovieController controller;

  MovieBloc(this.controller) : super(MovieInitial()) {
    on<SearchMovies>((event, emit) async {
      emit(MovieLoading());
      try {
        final movies = await controller.searchMovies(event.query);
        emit(MovieLoaded(movies));
      } catch (e) {
        emit(MovieError(e.toString()));
      }
    });
  }
}