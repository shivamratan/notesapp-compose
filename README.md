# Notes App

A modern, clean, and efficient Android Notes application built with the latest Jetpack libraries. This project demonstrates best practices in Android development, including Reactive UI, Dependency Injection, and local data persistence.

## 📺 Demo


[▶️ Watch Demo](https://github.com/user-attachments/assets/650912d1-6059-4100-b294-d63ced6137f3)





## 🚀 Features

- **CRUD Operations**: Create, read, update, and delete notes seamlessly.
- **Dynamic Theming**: Support for both Dark and Light modes with Material 3.
- **Responsive UI**: Built entirely with Jetpack Compose for a fluid user experience.
- **Persistence**: Uses Room database for local storage, ensuring your notes are safe even offline.
- **Modern Architecture**: Implements MVVM pattern with Hilt for dependency injection and StateFlow for reactive data updates.
- **Splash Screen**: Integration with the Android 12+ Splash Screen API.



## 🛠 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Theming**: [Material 3](https://developer.android.com/jetpack/compose/designsystems/material3)
- **Navigation**: [Jetpack Navigation](https://developer.android.com/guide/navigation)
- **DI**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Async**: [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) & [Flow](https://developer.android.com/kotlin/flow)
- **Language**: [Kotlin](https://kotlinlang.org/)
- **Reactive UI**: [StateFlow](https://developer.android.com/jetpack/compose/side-effects#stateflow)
- **Code Architecture**: [Pure MVVM](https://developer.android.com/jetpack/guide/ui-layer)




## 📂 Project Structure

```text
com.ratanapps.notesapp/
├── data/               # Data layer: Repository, Room Database, DAOs, and Entities
├── di/                 # Dependency Injection: Hilt Modules
├── ui/                 # UI layer
│   ├── notes/
│   │   ├── activity/   # Main Activity
│   │   ├── features/   # Feature-based UI (Home, Create)
│   │   ├── navigation/ # NavHost and Screen definitions
│   │   └── util/       # Compose-specific utilities
│   └── theme/          # Material 3 Theme definitions
└── utils/              # General helper classes and constants
```



## 🏗 Setup & Installation

1. Clone the repository.
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device (API 24+).



## 📝 License

This project is open-source and available under the MIT License.


