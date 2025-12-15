import 'package:flutter/material.dart';
import '../view/product_screen.dart';
import 'signup.dart'; 

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  String email = '';
  String password = '';
  bool passwordVisible = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF0A0E21),
      body: Stack(
        children: [
          Opacity(
            opacity: 0.05,
            child: Image.asset(
              'assets/images/login_background.png',
              width: double.infinity,
              height: double.infinity,
              fit: BoxFit.cover,
            ),
          ),
          SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 24.0, vertical: 60),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  const SizedBox(height: 50),
                  const Text("Welcome back!", style: TextStyle(fontSize: 30, color: Colors.white)),
                  const SizedBox(height: 10),
                  const Text("Please sign in to your account", style: TextStyle(fontSize: 15, color: Colors.white)),
                  const SizedBox(height: 50),

               
                  TextField(
                    onChanged: (value) => email = value,
                    style: const TextStyle(color: Colors.white),
                    decoration: InputDecoration(
                      labelText: "Email",
                      labelStyle: const TextStyle(color: Colors.white),
                      filled: true,
                      fillColor: const Color(0xFF1A1F36),
                      border: OutlineInputBorder(borderRadius: BorderRadius.circular(10), borderSide: BorderSide.none),
                    ),
                  ),
                  const SizedBox(height: 32),

                  TextField(
                    onChanged: (value) => password = value,
                    obscureText: !passwordVisible,
                    style: const TextStyle(color: Colors.white),
                    decoration: InputDecoration(
                      labelText: "Password",
                      labelStyle: const TextStyle(color: Colors.white),
                      filled: true,
                      fillColor: const Color(0xFF1A1F36),
                      border: OutlineInputBorder(borderRadius: BorderRadius.circular(10), borderSide: BorderSide.none),
                      suffixIcon: IconButton(
                        icon: Icon(passwordVisible ? Icons.visibility : Icons.visibility_off, color: Colors.white),
                        onPressed: () => setState(() => passwordVisible = !passwordVisible),
                      ),
                    ),
                  ),
                  const SizedBox(height: 50),

                  ElevatedButton(
                    onPressed: () {
                      if (email.isEmpty || password.isEmpty) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text("Please enter email and password"))
                        );
                        return;
                      }

                      final user = users.firstWhere(
                        (u) => u.email == email && u.password == password,
                        orElse: () => User(fullName: '', email: '', password: ''),
                      );

                      if (user.email.isEmpty) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text("User not found or wrong password"))
                        );
                        return;
                      }

                     
                      Navigator.push(context, MaterialPageRoute(builder: (context) => const MovieScreen()));
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: const Color(0xFF2196F3),
                      minimumSize: const Size(300, 50),
                      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                    ),
                    child: const Text("Sign in", style: TextStyle(color: Colors.white, fontSize: 18)),
                  ),
                  const SizedBox(height: 20),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text("Don't have an account?", style: TextStyle(color: Colors.white)),
                      TextButton(
                        onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const RegisterScreen())),
                        child: const Text("Sign Up", style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
                      )
                    ],
                  )
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
