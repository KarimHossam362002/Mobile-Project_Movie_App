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

# Overview
- Android movie application built with Jetpack Compose, Room, and Firebase.
- Follows MVVM architecture.
- Project is divided into roles for clarity and collaboration.

# Architecture
- UI layer: Jetpack Compose screens and reusable components.
- ViewModel layer: State management and business logic.
- Data layer: Repository, Room database, and Firebase.
- Testing layer: Unit tests, UI tests, and GitHub Actions.

# Member 1 – Android Backend and Database
- Responsible for the entire data layer.

## Room Database
- Define MovieEntity.kt.
- Create MovieDao.kt with insert, query, and delete operations.
- Set up AppDatabase.kt.
- Implement type converters if needed (e.g., storing a list of genres).

## Data Models
- Refine MovieFB.kt as the Firebase Data Transfer Object (DTO).
- Implement mapping between MovieFB and MovieEntity.

## Repository
- Implement MovieRepository.kt.
- Provide core functions:
  - getMovies()
  - getMovieById()
  - toggleFavorite()
- Load data from Room first.
- Fetch from Firebase if data is missing or stale.
- Cache updated data locally.

# Member 2 – Android UI and Frontend
- Responsible for Jetpack Compose UI and user experience.

## Screens
- HomeScreen.kt
- MovieDetailsScreen.kt
- FavoritesScreen.kt
- SearchScreen.kt
- Split HomeScreen into Home, Search, and Favorites.
- Use LazyColumn and LazyVerticalGrid for lists.

## UI Components
- Create reusable components inside ui/components.
- MovieCard
- SearchBar
- RatingStars
- Ensure theme support and responsive layouts.

## State Handling
- Observe ViewModel state.
- Handle user interactions such as clicks and text input.
- Forward events to ViewModel.
- Handle navigation between screens.

# Member 3 – Networking and Flutter
- Responsible for Firebase communication and Flutter integration.

## Firebase
- Implement FirebaseService.kt.
- Fetch all movies.
- Fetch a movie by ID.
- Update movie data such as favorites.
- Implement login, register, and logout.
- Create AuthViewModel.
- Ensure MovieFB matches Firebase data structure.

## Flutter Module (Optional)
- Set up Flutter module inside the Android project.
- Build Flutter UI such as profile or settings screen.
- Use MethodChannel for Kotlin and Dart communication.

# Member 4 – Testing, Documentation, and GitHub
- Responsible for quality, stability, and collaboration.

## GitHub
- Set up the repository.
- Protect the main branch.
- Review and merge pull requests.
- Resolve merge conflicts.

## Testing
- Write unit tests for ViewModels and Repository.
- Write UI tests using Jetpack Compose Test APIs.
- Run tests automatically using GitHub Actions on every pull request.

## Documentation
- Maintain the README file.
- Document architecture and technical decisions.
- Add KDoc comments for public classes and functions.








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