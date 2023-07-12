# Fitness Tracker App

We developed a fitness step tracker app that tracks three main activities during the day:
1. The number of steps
2. The distance traveled (calculated based on step count and user data)
3. The calories burned (calculated automatically based on distance covered and user data as well)

We use the SensorManager class of Android for implementation. Here we created a service making use of Sensor of type step counter built in the device for accurate step tracking. 

The app starts with a query of the user's basic biological data to adjust and calculate all measurements individually. User the number of steps set as a daily goal for the app to base on. 

https://github.com/CanWrobel/FitnessTrackerApp/assets/68116818/e43920af-2ae0-4900-b848-e81b76660a72

# Main page
The main page of the app shows the current progress of each measurement in the form of a circular progress bar. 
With the click of 'End day' you end your tracking and the day's data will be saved to the database.

![image](https://github.com/CanWrobel/FitnessTrackerApp/assets/68116818/1d0620de-ec98-4b32-ae52-084370371389)

# Streaks page
We also add a gamification feature that counts as an achievement for every day the user reaches their goal. If the user achieves more than one successful day in a row, this is called a streak. This streak function is displayed in the app with a calendar, with pink indicating current ongoing streak and gray being all past streaks broken by an 'unsuccessful' day.

![image](https://github.com/CanWrobel/FitnessTrackerApp/assets/68116818/33551536-2b10-4aa3-b8b0-83fb54ecad79)

# Other features
Other features other than Streaks we included as well are:
1. History: Lists all entries of a day's data from previous days.
2. Settings: View and change user data.
3. Input: Manually input your progress, just pick a date and number of steps you took.
   
## Installation

1. Clone this repository.
2. Open Android Studio.
3. Open this project.
4. Build and run.

Note: Simulating sensors doesn't work smoothly in Android Studio and virtual devices. To fully use the app's features, we recommend installing it in a real android device for the step counter to work. 

## Usage

1. Open the app.
2. Fill out your basic biological data.
3. Determine your step count goal.
4. Use the app throughout the day.
5. Check your progress on the main page!
