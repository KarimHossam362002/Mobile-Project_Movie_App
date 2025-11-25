🎬 Movie App – Android + Flutter (Hybrid Feature)

A modern movie browsing application built using Android (Kotlin, MVVM, Room, Retrofit) with an additional Flutter module to satisfy project grading requirements.

This project follows clean architecture, strong code quality, responsive UI design, proper persistence layer, and integrated testing.

👥 Team Members

Member 1 – Android Backend & Database Developer

Member 2 – Android UI/UX & Frontend Developer

Member 3 – Networking/API + Flutter Module Developer

Member 4 – Testing, Documentation & GitHub Manager

🏆 Project Grading Alignment

This section explains how our project meets each grading requirement.

🟦 Group Grades (Total: 14%)
✔ Feature Coverage (2%)

Implemented Jetpack Compose screens (Home, Details, Favorites)

Intent-based navigation between Android + Flutter module

Used Kotlin features:data classes, coroutines

✔ UI/UX Quality (2%)

Clean Material Design 3 UI

Responsive layouts for phones/tablets

Smooth animations

Dark/Light Mode support

✔ Code Quality (2%)

MVVM architecture

Repository Pattern

Clean Kotlin conventions (val over var, sealed classes, lambdas)

Modular package structure

✔ Persistence Data (2%)

Implemented Room Database with tables:

Movies

Genres

Actors

Favorites

Reviews

Watchlist

Full CRUD (Insert, Update, Delete, Query)



✔ Testing (2%)

Tools used:

JUnit – Unit tests for ViewModels

Mockito – Repository & DAO mocking

Espresso – Basic UI tests for search + favorites

✔ Flutter Integration (2%)

A lightweight Flutter screen created to demonstrate:

Material design basics

State handling

Communication between Android → Flutter (via Intent)

Flutter module included inside the Android project

✔ Stretch Feature (2%)

We implemented:

Firebase Authentication

Email/Password login

Connected with local Room user data

This satisfies the “feature not covered in class” requirement

📱 App Features
🎬 Movie Features

Browse trending movies

Search movies

View detailed info (actors, genres, rating)

Add/remove Favorites

Add/remove Watchlist

Post Reviews

👤 User Features

Register & Login via Firebase Auth

Sync user favorites locally

🌐 Networking

Retrofit API client

Coroutine-based async requests

Caching + fallback to Room

🔄 Flutter Screen

A Flutter mini-app that:

Displays a list of recommended movies

Allows back navigation to Android

Demonstrates basic Flutter widget layout & state management

🧩 Technologies Used
Layer	Technology
Language	Kotlin, Dart
UI	Jetpack Compose, Flutter Widgets
Architecture	MVVM, Repository
Database	Room
Networking	Retrofit + Coroutines
Testing	JUnit, Mockito, Espresso
Stretch	Firebase Authentication
Version Control	GitHub
📂 Project Structure
```
app/
│
├── data/
│   ├── dao/
│   ├── entities/
│   ├── database/
│   ├── repository/
│   └── network/
│
├── ui/
│   ├── home/
│   ├── details/
│   ├── favorites/
│   ├── watchlist/
│   └── auth/
│
├── flutter_module/
│   ├── lib/
│   └── pubspec.yaml
│
└── tests/
```
👥 Task Distribution (4 Members)

Below is a clean and fair task division mapped directly to the rubric categories.

🧑‍💻 Member 1 – Backend & Database (Kotlin)
Responsibilities

Designed SQL schema + ERD

Implemented Room Entities (Movies, Genres, Actors, Reviews, Favorites)

Built DAOs + Repository

Added CRUD operations

Integrated Firebase Auth

Handled offline cache logic

Rubric Match:

✔ Code Quality
✔ Persistence Data
✔ Feature Coverage

🎨 Member 2 – UI/UX & Compose Developer
Responsibilities

Designed home screen, movie list, details screen

Built navigation (Compose Navigation)

Responsive layouts & Material 3

Implemented Favorites + Watchlist UI

Created animations, splash screen

Rubric Match:

✔ UI/UX Quality
✔ Feature Coverage

🌐 Member 3 – API + Flutter Developer
Responsibilities

Integrated Retrofit API

Built API service interfaces

Managed network responses + DTO mapping

Created Flutter mini-app (Material UI + State handling)

Connected Android → Flutter via Intent

Rubric Match:

✔ Feature Coverage
✔ Flutter Requirement
✔ Stretch Feature Support (API integration)

🧪 Member 4 – Testing, Documentation & GitHub
Responsibilities

Wrote JUnit tests for ViewModels

Mockito tests for repository & DAO

Espresso UI tests

Managed GitHub branches + pull requests

Prepared README, UML, ERD

Ensured each member had visible contributions

Rubric Match:

✔ Testing
✔ Contribution Evidence
✔ Individual Reflection

🧪 Testing Summary

JUnit: MovieViewModel, AuthViewModel

Mockito: MovieRepository, Fake DAO

Espresso: Search bar, Favorites screen

Flutter tests: Basic Widget test

📸 Screenshots (Optional)

<p align="center">
  <a href="#" target="_blank">
    <img src="#" alt="UI design images" border="0" width="700" />
  </a>
</p>

🎤 Individual Reflections

Each member provided a short reflection during presentation, demonstrating:

Full understanding of their part

Ability to answer random questions

Awareness of total project architecture

▶️ How to Run
Android
Open project in Android Studio
Sync Gradle
Run app on emulator or device

Flutter Module
```
cd flutter_module
flutter pub get
flutter run
```