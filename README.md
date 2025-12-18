# 🎬 Movie App – Android + Flutter (Hybrid Feature)

📂 Project Structure

```
app/src/main/java/com/example/movieapp/
│
├── data/
│   │
│   ├── model/
│   │   ├── MovieFB.kt          // Data Transfer Object (DTO) for Firebase network calls.
│   │   └── MovieEntity.kt      // Represents the movie table in the local Room database.
│   │
│   ├── local/
│   │   ├── MovieDao.kt         // DAO: Contains SQL queries for Room (Insert, Get, Delete).
│   │   └── AppDatabase.kt      // The main Room database class.
│   │
│   ├── remote/
│   │   └── FirebaseService.kt  // Handles all communication with Firebase (e.g., fetching movies).
│   │
│   └── repository/
│       └── MovieRepository.kt    // Single source of truth. Decides whether to fetch data
│                                 // from Firebase (remote) or the local Room database.
│
├── ui/
│   │
│   ├── components/             // Shared, reusable UI components like MovieCard, SearchBar.
│   │   ├── MovieCard.kt
│   │   └── SearchBar.kt
│   │
│   ├── home/
│   │   ├── HomeScreen.kt       // The UI for the home screen.
│   │   └── HomeViewModel.kt      // ViewModel ONLY for the home screen.
│   │
│   ├── details/
│   │   ├── MovieDetailsScreen.kt
│   │   └── MovieDetailsViewModel.kt
│   │
│   ├── favorites/
│   │   ├── FavoritesScreen.kt
│   │   └── FavoritesViewModel.kt
│   │
│   ├── search/
│   │   ├── SearchScreen.kt
│   │   └── SearchViewModel.kt
│   │
│   └── auth/                     // For login/registration screens.
│       ├── LoginScreen.kt
│       └── AuthViewModel.kt
│
├── di/                           // Optional but highly recommended: Dependency Injection
│   └── AppModule.kt              // (e.g., using Hilt) to provide instances of Repository, DAO, etc.
│
└── navigation/
    └── AppNavigation.kt          // Defines all navigation routes and the NavHost.


```


#!/bin/bash

Movie App Readme

Overview
Android movie application built with Jetpack Compose, Room, and Firebase.
Follows MVVM architecture.
Project is divided into roles for clarity and collaboration.

Architecture
UI Layer: Jetpack Compose screens and components
ViewModel Layer: State management and business logic
Data Layer: Repository, Room database, Firebase
Testing: Unit tests, UI tests, GitHub Actions

Member 1 – Android Backend And Database
Responsible for the data layer.

Room Database
Define MovieEntity.kt
Create MovieDao.kt with insert, query, delete
Set up AppDatabase.kt
Add type converters if needed (for example, list of genres)

Data Models
Refine MovieFB.kt as Firebase DTO
Mapping between MovieFB and MovieEntity

Repository
Implement MovieRepository.kt
Functions:
getMovies()
getMovieById()
toggleFavorite()

Logic
Read from Room first
Fetch from Firebase if data is missing or stale
Cache result locally

Member 2 – Android UI And Frontend
Responsible for Jetpack Compose UI.

Screens
HomeScreen.kt
MovieDetailsScreen.kt
FavoritesScreen.kt
SearchScreen.kt
Split HomeScreen into Home, Search, Favorites
Use LazyColumn and LazyVerticalGrid

UI Components
Reusable components in ui/components
MovieCard
SearchBar
RatingStars
Support themes and different screen sizes

State Handling
Observe ViewModel state
Handle clicks and text input
Send events to ViewModel
Handle navigation

Member 3 – Networking And Flutter
Responsible for Firebase and Flutter module.

Firebase
Implement FirebaseService.kt
Fetch all movies
Fetch movie by ID
Update movie data (favorites)
Implement login, register, logout
Create AuthViewModel
Keep MovieFB in sync with Firebase

Flutter Module (Optional)
Set up Flutter module
Build Flutter UI (profile or settings)
Use MethodChannel for Kotlin to Dart communication

Member 4 – Testing, Documentation, And GitHub
Responsible for quality and collaboration.

GitHub
Set up repository
Protect main branch
Review and merge pull requests
Resolve conflicts

Testing
Unit tests for ViewModels and Repository
UI tests using Compose Test API
CI using GitHub Actions on every pull request

Documentation
Maintain README
Document architecture decisions
Add KDoc for public classes and functions







📸 Screenshots

<p align="center">
<a href="https://l.top4top.io/p_363995nbn1.png" target="_blank">
<img src="https://l.top4top.io/p_363995nbn1.png" alt="UI design images" border="0" width="700" />
</a>
</p>



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