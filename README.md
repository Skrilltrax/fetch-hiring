# Fetch Hiring

A sample app which allows users to fetch a list of items and display it in a sorted and grouped manner. This was built as the take home exercise for my interview with [Fetch](https://fetch.com/).

## Tech stack

The app currently uses an MVVM architecture. The whole app is divided into multiple packages:

- API Package: Contains the ItemService which fetches data from the internet using the networking library Retrofit and the efficient JSON serialization library Moshi.

- Data Package: This package houses the core data handling logic. The FetchItemRepository retrieves data from the ItemService and the GroupedSortedListUseCase applies any necessary business logic before exposing the data for use.

- UI Package: This package is further divided:
  - Theme Package: Defines the base application theme, ensuring a consistent look and feel.
  - Home Package: Contains the building blocks for the home screen: the HomeScreen UI itself, the HomeScreenUiState class which represents the data displayed on the screen, and the HomeScreenViewModel which acts as the bridge between the UI and the Data layer.

- DI Package: This package leverages dependency injection with NetworkModule, RepositoryModule, and CoroutinesModule to provide and manage the objects used throughout the app. These modules are configured with the powerful dependency injection framework Dagger/Hilt.

- Model Package: The foundation of the app's data representation lies within this package. It defines the core data structures with classes like ListItem and ListItemDto.

The libraries that this app is using as of now:

- [Jetpack Compose](https://developer.android.com/jetpack/compose) + [Material Design 3](https://material.io/develop/android) for UI
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for hot flows
- [Dagger](https://dagger.dev/) + [Hilt](https://dagger.dev/hilt/) for Dependency Injection
- [JUnit4](https://junit.org/junit4/) for unit tests and [Turbine](https://github.com/cashapp/turbine) for testing hot flows

## Things missing from tech stack

### Navigation
The app only consists of one screen and hence does not require a navigation library (AndroidX Navigation).

### Persistence/Database
The app does not persist any user data so it does not require a database (Room/SQLDelight).

## Testing

The application leverages unit tests to ensure core functionalities operate as expected under various conditions. Currently, unit tests cover the FetchItemRepository, GroupedSortedListUseCase, and HomeScreenViewModel classes. These tests play a crucial role in maintaining application consistency and error handling by verifying behavior across different scenarios.

While the current focus is on unit testing core functionalities, the application can be extended with UI testing for visual regressions or component interactions. Snapshot testing or integration tests can be valuable tools for this purpose.

## Screenshots

<img src="https://github.com/Skrilltrax/fetch-hiring/assets/10516866/28eef2fd-7a8c-474d-8ad3-c4a215c7e0f4" width="200px" />  <img src="https://github.com/Skrilltrax/fetch-hiring/assets/10516866/8fd708d1-07d6-4bcd-899a-c9cf9c277732" width="200px" /> <img src="https://github.com/Skrilltrax/fetch-hiring/assets/10516866/d94f1960-1e74-414e-8d3f-bfda77d7aad1" width="200px" />
  
<img src="https://github.com/Skrilltrax/fetch-hiring/assets/10516866/b7cd1e72-8a79-46ae-bf11-a7725a3d18c5" width="200px" />  <img src="https://github.com/Skrilltrax/fetch-hiring/assets/10516866/33e86c30-dec1-4efe-999f-2c331ecb1296" width="200px" /> <img src="https://github.com/Skrilltrax/fetch-hiring/assets/10516866/055dd623-f8d4-471a-a349-b4bb2968156b" width="200px" />

# License

```
Copyright (c) 2024 Aditya Wasan
Permission is hereby granted, free of charge, to any
person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the
Software without restriction, including without
limitation the rights to use, copy, modify, merge,
publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software
is furnished to do so, subject to the following
conditions:
The above copyright notice and this permission notice
shall be included in all copies or substantial portions
of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF
ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT
SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.
```
