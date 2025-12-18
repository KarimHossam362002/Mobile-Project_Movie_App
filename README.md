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


# Movie App

# Overview
# android movie application built with jetpack compose, room, firebase
# follows mvvm architecture
# project is divided into roles for clarity and collaboration

# Architecture
# ui layer: jetpack compose screens and components
# viewmodel layer: state and business logic
# data layer: repository, room database, firebase
# testing: unit tests, ui tests, github actions

# Member 1 - android backend and database
# responsible for data layer

# room database
# define MovieEntity.kt
# create MovieDao.kt with insert, query, delete
# setup AppDatabase.kt
# add type converters if needed (e.g. list of genres)

# data models
# refine MovieFB.kt as firebase dto
# mapping between MovieFB and MovieEntity

# repository
# implement MovieRepository.kt
# functions:
# - getMovies()
# - getMovieById()
# - toggleFavorite()
# logic:
# - read from room first
# - fetch from firebase if data is missing or stale
# - cache result locally

# Member 2 - android ui and frontend
# responsible for jetpack compose ui

# Screens
# HomeScreen.kt
# MovieDetailsScreen.kt
# FavoritesScreen.kt
# SearchScreen.kt
# split homescreen into home, search, favorites
# use LazyColumn and LazyVerticalGrid

# Ui components
# reusable components in ui/components
# MovieCard
# SearchBar
# RatingStars
# support themes and different screen sizes

# State handling
# observe viewmodel state
# handle clicks and text input
# send events to viewmodel
# handle navigation

# Member 3 - networking and flutter
# responsible for firebase and flutter module

# Firebase
# implement FirebaseService.kt
# fetch all movies
# fetch movie by id
# update movie data (favorites)
# implement login, register, logout
# create AuthViewModel
# keep MovieFB in sync with firebase

# Flutter module (optional)
# setup flutter module
# build flutter ui (profile or settings)
# use MethodChannel for kotlin <-> dart communication

# Member 4 - testing, documentation, github
# responsible for quality and collaboration

# Github
# setup repository
# protect main branch
# review and merge pull requests
# resolve conflicts

# Testing
# unit tests for viewmodels and repository
# ui tests using compose test api
# ci with github actions on every pull request

# Documentation
# maintain readme
# document architecture decisions
# add kdoc for public classes and functions






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