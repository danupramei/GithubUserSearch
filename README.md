# GithubUserSearch
📌 Prerequisites

Before you proceed, ensure you have the following installed on your system:

    Android Studio (latest stable version)
    JDK 17 or higher
    Gradle (bundled with Android Studio)
    Android SDK (with API Level 33 or higher)
    Internet connection (for dependency resolution)

🚀 Steps to Build and Run the Project
1️⃣ Clone the Repository

Open a terminal and run the following command to clone the project from GitHub:

git clone https://github.com/danupramei/GithubUserSearch.git

Then, navigate into the project directory:

cd GithubUserSearch

2️⃣ Open the Project in Android Studio

    Open Android Studio
    Click "Open" and select the project folder
    Wait for Gradle to sync (this may take a few minutes)

If Gradle sync fails, try the following:

    Ensure you have a stable internet connection
    Click File > Invalidate Caches & Restart
    Manually sync Gradle via File > Sync Project with Gradle Files

3️⃣ Set Up API Keys

create a local.properties file in the root of the project and add:

API_KEY="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"

Then, sync Gradle again.
4️⃣ Build the Project

Click on Build > Make Project or run the following command in the terminal:

./gradlew assembleDebug

For a release build, use:

./gradlew assembleRelease

5️⃣ Run the Project on an Emulator or Device

    Emulator:
        Open AVD Manager in Android Studio
        Create a new emulator (API 33 or higher recommended)
        Click Run

    Physical Device:
        Enable Developer Options and USB Debugging
        Connect the device via USB
        Run the following command:

./gradlew installDebug

Or click Run ▶ in Android Studio.


Application Features Description

This application provides a seamless experience for users to search for GitHub users, view their details, and store data locally while following best practices in Android development. Below is a detailed description of the features:
1. Search Screen
    Users can search for GitHub users using a dedicated search screen.
    The app uses Retrofit and OkHttp for network requests to fetch data from the GitHub API.
    Search results are displayed in a RecyclerView, ensuring an optimized and smooth UI experience.

2. User List Screen
    Displays a list of GitHub users, including their usernames and avatars.
    The app uses the Glide library for efficient image loading and caching, ensuring a smooth user experience.

3. User Detail Screen
    A dedicated screen to display detailed information about a selected GitHub user.
    Includes user details such as username, avatar, bio, and other publicly available information.

4. Local Data Persistence
    Uses Room Database for local data storage to persist user information.
    Implements data caching to provide offline access and optimize network usage.

5. Clean Architecture
    Follows Clean Architecture principles for better maintainability and scalability.
    Implements MVVM (Model-View-ViewModel) architecture to separate concerns.
    Divides the project into clear layers:
        Domain Layer: Contains business logic and use cases.
        Data Layer: Handles API calls and database operations.
        Presentation Layer: Manages UI and user interactions.
    Uses Hilt to manage dependencies efficiently.

6. Concurrency
    Uses Kotlin Coroutines to handle asynchronous operations efficiently.
    Implements Jetpack LiveData and ViewModel for UI state management and lifecycle-aware data handling.

7. Git History
    Maintains a clean and descriptive Git history to track changes effectively.
    Ensures meaningful commit messages to facilitate collaboration and debugging.

🔹 Additional Features
1. Debugging
    Integrates Chucker to intercept and debug network requests during development.
    Allows developers to monitor API calls and responses directly within the app.

2. Dependency Versioning
    Uses Version Catalog to manage dependency versions, ensuring consistency and easy updates.

3. Work Manager
    Implements WorkManager for handling background tasks.

4. JSON Parsing
    Uses Moshi for efficient and type-safe JSON parsing.
    Ensures proper data serialization and deserialization for API responses.
5. Single Activity
    Use one activity for all screen, it will improved performance & memory usage
6. Navigation Component
    Navigation Component is part of Jetpack and helps simplify app navigation by handling Fragment transactions, deep linking, and UI state preservation.
7. Modularization
    Modularization in Android development is the practice of splitting an application into multiple, independent modules to improve scalability, maintainability, and reusability.
