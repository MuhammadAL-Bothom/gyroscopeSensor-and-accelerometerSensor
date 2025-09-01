# Android Sensors Demo (Java)

## Overview
This project demonstrates how to access and display data from the **Gyroscope** and **Accelerometer** sensors in an Android device.  
The app shows live **X, Y, Z** values for both sensors and updates them in real-time as the device is moved or rotated.

---

## Features
- ✅ Reads **Gyroscope** rotation values (rad/s)  
- ✅ Reads **Accelerometer** acceleration values (m/s²)  
- ✅ Displays values on-screen in two `TextView`s  
- ✅ Logs sensor updates using `Log.d` for debugging  
- ✅ Automatically registers listeners in `onResume()` and unregisters in `onPause()` to save battery  
- ✅ Handles unavailable sensors gracefully with Toast messages  

---
## Screenshots
<p align="center">
  <img src="https://github.com/user-attachments/assets/d97cbcd6-725c-4e7a-93ea-afcd24fe9281" width="250" alt="Gyroscope and Accelerometer values"/>
</p>

---

## Key Files
- **MainActivity.java** – sets up `SensorManager`, registers/unregisters listeners, and updates UI with sensor values.
- **activity_main.xml** – contains:
  - `gyroTextView`: displays gyroscope values  
  - `accelTextView`: displays accelerometer values  

---

## How It Works
1. The app initializes a `SensorManager` and attempts to load:
   - `TYPE_GYROSCOPE`
   - `TYPE_ACCELEROMETER`
2. If sensors are available, listeners are registered at a normal delay (`SENSOR_DELAY_NORMAL`).
3. In `onSensorChanged()`:
   - Gyroscope data updates the **Rotation** (X, Y, Z).
   - Accelerometer data updates the **Acceleration** (X, Y, Z).
4. Results are shown on-screen and logged for debugging.

---

## Requirements
- Android Studio Ladybug or newer
- Minimum SDK: 21 (Android 5.0 Lollipop)
- Java 8 or higher

---

## Usage
1. Clone or download the project.
2. Open in **Android Studio**.
3. Run on a physical device or emulator with sensor simulation.
4. Move or tilt the device to see **Gyroscope** and **Accelerometer** values update in real time.

---

## Notes
- Some emulators may not provide real gyroscope/accelerometer values. For accurate testing, use a real device.
- No special permissions are required for these sensors.
