import 'package:flutter/material.dart';

import 'login.dart';


class User {
  final String fullName;
  final String email;
  final String password;

  User({required this.fullName, required this.email, required this.password});
}
List<User> users = [];

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({super.key});

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  String fullName = '';
  String email = '';
  String password = '';
  String confirmPassword = '';
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
                  const SizedBox(height: 20),
                  const Text(
                    "Create an account",
                    style: TextStyle(fontSize: 30, color: Colors.white),
                  ),
                  const SizedBox(height: 10),
                  const Text(
                    "Please fill the form to continue",
                    style: TextStyle(fontSize: 15, color: Colors.white),
                  ),
                  const SizedBox(height: 50),

                  // Full Name
                  TextField(
                    onChanged: (value) => fullName = value,
                    style: const TextStyle(color: Colors.white),
                    decoration: InputDecoration(
                      labelText: "Full Name",
                      labelStyle: const TextStyle(color: Colors.white),
                      filled: true,
                      fillColor: const Color(0xFF1A1F36),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(10),
                        borderSide: BorderSide.none,
                      ),
                    ),
                  ),
                  const SizedBox(height: 25),

                  // Email
                  TextField(
                    onChanged: (value) => email = value,
                    style: const TextStyle(color: Colors.white),
                    decoration: InputDecoration(
                      labelText: "Email",
                      labelStyle: const TextStyle(color: Colors.white),
                      filled: true,
                      fillColor: const Color(0xFF1A1F36),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(10),
                        borderSide: BorderSide.none,
                      ),
                    ),
                  ),
                  const SizedBox(height: 25),

                  // Password
                  TextField(
                    onChanged: (value) => password = value,
                    obscureText: !passwordVisible,
                    style: const TextStyle(color: Colors.white),
                    decoration: InputDecoration(
                      labelText: "Password",
                      labelStyle: const TextStyle(color: Colors.white),
                      filled: true,
                      fillColor: const Color(0xFF1A1F36),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(10),
                        borderSide: BorderSide.none,
                      ),
                      suffixIcon: IconButton(
                        icon: Icon(
                          passwordVisible ? Icons.visibility : Icons.visibility_off,
                          color: Colors.white,
                        ),
                        onPressed: () {
                          setState(() {
                            passwordVisible = !passwordVisible;
                          });
                        },
                      ),
                    ),
                  ),
                  const SizedBox(height: 25),

                  // Confirm Password
                  TextField(
                    onChanged: (value) => confirmPassword = value,
                    obscureText: !passwordVisible,
                    style: const TextStyle(color: Colors.white),
                    decoration: InputDecoration(
                      labelText: "Confirm Password",
                      labelStyle: const TextStyle(color: Colors.white),
                      filled: true,
                      fillColor: const Color(0xFF1A1F36),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(10),
                        borderSide: BorderSide.none,
                      ),
                      suffixIcon: IconButton(
                        icon: Icon(
                          passwordVisible ? Icons.visibility : Icons.visibility_off,
                          color: Colors.white,
                        ),
                        onPressed: () {
                          setState(() {
                            passwordVisible = !passwordVisible;
                          });
                        },
                      ),
                    ),
                  ),
                  const SizedBox(height: 50),

                 
                  ElevatedButton(
                    onPressed: () {
                      if (fullName.isEmpty || email.isEmpty || password.isEmpty || confirmPassword.isEmpty) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text("Please fill all fields"))
                        );
                        return;
                      }
                      if (password.length < 6) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text("Password must be at least 6 characters"))
                        );
                        return;
                      }
                      if (password != confirmPassword) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text("Passwords do not match"))
                        );
                        return;
                      }

                    
                      users.add(User(fullName: fullName, email: email, password: password));

                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text("Account created successfully"))
                      );
                      Navigator.push(context, MaterialPageRoute(builder: (context) => const LoginScreen()));
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: const Color(0xFF2196F3),
                      minimumSize: const Size(300, 50),
                      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                    ),
                    child: const Text("Sign Up", style: TextStyle(color: Colors.white, fontSize: 18)),
                  ),
                  const SizedBox(height: 20),

            
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const Text("Have an account?", style: TextStyle(color: Colors.white)),
                      TextButton(
                        onPressed: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const LoginScreen())),
                        child: const Text(
                          "Sign In",
                          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
                        ),
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
