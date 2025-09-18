## 📹 Demo

### App in Action
> 🎬 **Screen Recording Placeholder**  
> *Add a screen recording here showing the app's key features: loading holdings, expanding/collapsing summary, smooth animations, and error handling*

### Screenshots

<div align="center">
  <img src="https://via.placeholder.com/300x600/1976D2/FFFFFF?text=Portfolio+Screen" alt="Portfolio Screen" width="250"/>
  <img src="https://via.placeholder.com/300x600/388E3C/FFFFFF?text=Expanded+Summary" alt="Expanded Summary" width="250"/>
  <img src="https://via.placeholder.com/300x600/F57C00/FFFFFF?text=Loading+State" alt="Loading State" width="250"/>
</div>

> - *Main portfolio screen with holdings list*
> - *Expanded summary bar with portfolio metrics*
> - *Loading and error states*

## 🏗️ Architecture

This project follows **Clean Architecture** principles with clear separation of concerns:

```
app/
├── data/           # Data layer - Remote APIs, DTOs, Repositories
│   ├── di/         # Dependency injection modules
│   ├── mapper/     # Data mapping between DTOs and domain models
│   ├── remote/     # Network API services and DTOs
│   └── repository/ # Repository implementations
├── domain/         # Business logic layer
│   ├── model/      # Domain models
│   ├── repository/ # Repository interfaces
│   └── usecase/    # Business use cases
├── ui/             # Presentation layer
│   ├── component/  # Reusable UI components
│   ├── navigation/ # App navigation setup
│   ├── portfolio/  # Portfolio feature screens
│   └── theme/      # App theming and styling
└── core/           # Core utilities and shared code
```

### Key Architectural Patterns

- **MVVM (Model-View-ViewModel)**: UI layer follows MVVM pattern with ViewModels managing UI state
- **Repository Pattern**: Abstraction layer for data access
- **Use Case Pattern**: Encapsulation of business logic in single-responsibility classes
- **Dependency Injection**: Using Hilt for dependency management
- **Unidirectional Data Flow**: State flows down, events flow up

## 🛠️ Tech Stack

### Core Android
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Android SDK 36** - Target and compile SDK
- **Minimum SDK 31** - Supporting modern Android features

### Architecture & DI
- **Hilt** - Dependency injection framework
- **ViewModel** - UI-related data holder, lifecycle aware
- **StateFlow** - Reactive state management
- **Coroutines** - Asynchronous programming

### Networking
- **Retrofit** - HTTP client for API communication
- **OkHttp** - HTTP/HTTP2 client with interceptors
- **Kotlinx Serialization** - JSON serialization/deserialization

### UI & Navigation
- **Jetpack Compose** - Ensuring compatible Compose versions
- **Material 3** - Material Design 3 components
- **Material Icons Extended** - Comprehensive icon set
- **Navigation Compose** - Type-safe navigation
- **Hilt Navigation Compose** - ViewModel injection in navigation

### Testing
- **JUnit 4** - Unit testing framework
- **Mockito** - Mocking framework for tests
- **Espresso** - UI testing framework
- **Compose Testing** - Compose-specific testing utilities

## 🔧 Build Configuration

### Gradle Configuration
- **Kotlin DSL**: Modern Gradle build scripts
- **Version Catalogs**: Centralized dependency management in `libs.versions.toml`
- **KSP**: Kotlin Symbol Processing for annotation processing
- **Build Config**: Configuration for different build variants

## 🧪 Testing

Comprehensive testing strategy:

- **Unit Tests**: Business logic and use case testing
- **Integration Tests**: Not yet added
- **UI Tests**: Not yet added
- **Instrumented Tests**: Not yet added

Run tests:
```bash
./gradlew test                   
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK 36
