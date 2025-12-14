import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'bloc/product_bloc.dart';
import 'controlloer/product_controller.dart';
import 'view/product_screen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final MovieController controller = MovieController();

  MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (_) => MovieBloc(controller),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        home: MovieScreen(),
      ),
    );
  }
}
