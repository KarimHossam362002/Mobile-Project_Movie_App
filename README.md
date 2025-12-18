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

# =========================================================
# MOVIE APP – TEAM ROLES & RESPONSIBILITIES
# =========================================================

# ---------------------------------------------------------
# PROJECT OVERVIEW
# ---------------------------------------------------------
# Android Movie Application built using:
# - Jetpack Compose
# - Room Database
# - Firebase
# - MVVM Architecture
#
# The project is divided into clear roles to ensure:
# - Scalability
# - Maintainability
# - Efficient collaboration

# ---------------------------------------------------------
# PROJECT ARCHITECTURE
# ---------------------------------------------------------
# - UI Layer        : Jetpack Compose screens & components
# - ViewModel Layer : State management & business logic
# - Data Layer      : Repository, Room, Firebase
# - Testing & CI    : Unit tests, UI tests, GitHub Actions


# =========================================================
# TEAM ROLES & RESPONSIBILITIES
# =========================================================

# ---------------------------------------------------------
# MEMBER 1 – Android Backend & Database Developer
# ---------------------------------------------------------
# Role Summary:
# Owns the entire data layer. Ensures data is fetched,
# cached, and delivered reliably to ViewModels.

# Core Responsibilities:
#
# 1) Local Database (Room)
# ---------------------------------------------------------
# - Define MovieEntity.kt
# - Create MovieDao.kt with:
#     @Insert
#     @Query
#     @Delete
# - Set up AppDatabase.kt
# - Implement TypeConverters if needed
#   (e.g., list of genres)
#
# 2) Data Models
# ---------------------------------------------------------
# - Refine MovieFB.kt as Firebase DTO
# - Mapping functions:
#     MovieFB -> MovieEntity
#     MovieEntity -> MovieFB
#
# 3) Repository
# ---------------------------------------------------------
# - Implement MovieRepository.kt
# - Core functions:
#     getMovies()
#     getMovieById()
#     toggleFavorite()
# - Logic:
#     * Load from Room first
#     * If missing or stale:
#         -> Fetch from Firebase
#     * Cache results locally


# ---------------------------------------------------------
# MEMBER 2 – Android UI/UX & Frontend Developer
# ---------------------------------------------------------
# Role Summary:
# Builds responsive UI using Jetpack Compose and
# consumes ViewModel state.

# Core Responsibilities:
#
# 1) Screen Implementation
# ---------------------------------------------------------
# - Screens:
#     HomeScreen.kt
#     MovieDetailsScreen.kt
#     FavoritesScreen.kt
#     SearchScreen.kt
# - Refactor homescreen.kt into:
#     Home
#     Search
#     Favorites
# - Use:
#     LazyVerticalGrid
#     LazyColumn
#
# 2) UI Components
# ---------------------------------------------------------
# - Reusable components (ui/components/):
#     MovieCard
#     SearchBar
#     RatingStars
# - Ensure:
#     * Theme support
#     * Responsive layouts
#
# 3) State Handling
# ---------------------------------------------------------
# - Observe:
#     movie lists
#     loading states
#     error states
# - Handle user actions:
#     clicks
#     text input
# - Forward events to ViewModels
# - Implement navigation logic


# ---------------------------------------------------------
# MEMBER 3 – Networking & API + Flutter Module Developer
# ---------------------------------------------------------
# Role Summary:
# Manages Firebase communication and Flutter integration.

# Core Responsibilities:
#
# 1) Networking Layer (Firebase)
# ---------------------------------------------------------
# - Implement FirebaseService.kt
# - Functions:
#     fetch all movies
#     fetch movie by ID
#     update movie data (favorites)
# - Authentication:
#     Login
#     Register
#     Logout
# - Create AuthViewModel
# - Align Firebase data with MovieFB model
#
# 2) Flutter Module Integration (Optional)
# ---------------------------------------------------------
# - Set up Flutter module
# - Build Flutter UI (profile/settings)
# - Use MethodChannel:
#     Kotlin <-> Dart
# - Enable data exchange


# ---------------------------------------------------------
# MEMBER 4 – Testing, Documentation & GitHub Manager
# ---------------------------------------------------------
# Role Summary:
# Ensures quality, stability, documentation, and
# smooth collaboration.

# Core Responsibilities:
#
# 1) Version Control (GitHub)
# ---------------------------------------------------------
# - Set up repository
# - Branch protection:
#     PR required for main
# - Review & merge PRs
# - Resolve merge conflicts
# - Maintain main branch stability
#
# 2) Testing
# ---------------------------------------------------------
# - Unit tests:
#     ViewModels
#     MovieRepository
# - UI tests (Compose):
#     navigation
#     user flows
# - CI/CD:
#     GitHub Actions
#     run tests on every PR
#
# 3) Documentation
# ---------------------------------------------------------
# - Maintain README.md
# - Document architecture decisions
# - Add KDoc for:
#     public classes
#     public functions


# ---------------------------------------------------------
# GETTING STARTED
# ---------------------------------------------------------
# - Add setup instructions
# - Firebase configuration
# - Build & run steps


# ---------------------------------------------------------
# LICENSE
# ---------------------------------------------------------
# Add license information if applicable

# =========================================================
# END OF FILE
# =========================================================






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