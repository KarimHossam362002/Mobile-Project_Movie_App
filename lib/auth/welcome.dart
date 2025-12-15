import 'package:flutter/material.dart';
import 'dart:ui' as ui;

import 'package:movie/auth/login.dart';

class WelcomeScreen extends StatelessWidget {


  const WelcomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
         
          Image.asset(
            '.dart_tool/Images/WhatsApp Image 2025-12-15 at 1.17.59 PM.jpeg',
            width: double.infinity,
            height: double.infinity,
            fit: BoxFit.cover,
          ),
        
          Align(
            alignment: Alignment.bottomCenter,
            child: ClipPath(
              clipper: InwardWaveClipper(),
              child: Container(
                color: const Color(0xFF0A0E21), 
                width: double.infinity,
                padding: const EdgeInsets.fromLTRB(24, 60, 24, 48),
                
                child: Column(
                  
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    SizedBox(height: 18,),
                    const Text(
                      "Absolute Cinema",
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 35,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 16),
                    const Text(
                      "Welcome to the show\nGet ready to dive into the world of Hollywood",
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 15,
                      ),
                    ),
                    const SizedBox(height: 48),
                    SizedBox(
                      width: 300,
                      height: 56,
                      child: ElevatedButton(
                        onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (context)=>LoginScreen())),
                        style: ElevatedButton.styleFrom(
                          backgroundColor: const Color(0xFF2196F3), // Blue
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                        child: const Text(
                          "Get Started",
                          style: TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.w600,
                            color: Colors.white
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}


class InwardWaveClipper extends CustomClipper<Path> {
  @override
  Path getClip(Size size) {
    final path = Path();
    final width = size.width;

    path.moveTo(0, 0);
    path.quadraticBezierTo(width * 0.25, 120, width * 0.5, 60);
    path.quadraticBezierTo(width * 0.75, 0, width, 100);
    path.lineTo(width, size.height);
    path.lineTo(0, size.height);
    path.close();

    return path;
  }

  @override
  bool shouldReclip(covariant CustomClipper<Path> oldClipper) => false;
}
