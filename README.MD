# Rock Paper Scissors Game

## Overview
This project is an Android application that simulates a Rock Paper Scissors game with additional options like Lizard and Spock. The game allows users to play against the computer and keeps track of scores using an SQLite database.

## Features
- Play Rock Paper Scissors Lizard Spock against the computer.
- Timer-based gameplay.
- Score tracking and storage using SQLite.
- User-friendly interface with image assets for game choices.

## Technologies Used
- Java
- Android SDK
- SQLite
- Gradle

## Setup Instructions
1. **Clone the repository:**
   ```sh
   git clone https://github.com/Scot8/rock-paper-scissors.git
   cd rock-paper-scissors
   ```

2. **Open the project in Android Studio:**
   - Open Android Studio.
   - Select `Open an existing project`.
   - Navigate to the cloned repository and select it.

3. **Build the project:**
   - Ensure you have the necessary dependencies in your `build.gradle` file:
     ```gradle
     dependencies {
         implementation 'androidx.sqlite:sqlite:2.1.0'
     }
     ```
   - Sync the project with Gradle files.

4. **Run the application:**
   - Connect an Android device or start an emulator.
   - Click on the `Run` button in Android Studio.

## Code Structure
- `Activity3.java`: Main activity for the game logic and UI interactions.
- `DatabaseHelper.java`: Manages database creation and version management.
- `ScoreRepository.java`: Handles database operations for storing and retrieving scores.
- `Score.java`: Model class for managing user and computer scores.

## Usage
- Launch the application on your Android device.
- Enter your name and select the game mode.
- Click on the card to make your choice and play against the computer.
- The game will display the winner and update the scores accordingly.

## Contributing
If you would like to contribute to this project, please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact
For any inquiries or issues, please contact [Scot8](https://github.com/Scot8).

---

This README file provides an overview of the project, setup instructions, and usage details to help recruiters understand the application and its features.
