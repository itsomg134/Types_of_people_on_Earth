# Types of People on Earth – Spin Wheel

> **"Everyone is different. Spin the wheel and discover which type of person you are!"**

[**Play and Discover**](https://types-of-people-on-earth.vercel.app/)

<img width="1886" height="1291" alt="Screenshot_23-7-2026_222329_127 0 0 1" src="https://github.com/user-attachments/assets/31e368b3-76f3-4e7c-8d24-c09a57ed33b6" />

[![GitHub release](https://img.shields.io/github/v/release/your-username/TypesOfPeopleOnEarth)](https://github.com/your-username/TypesOfPeopleOnEarth/releases)
[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Material Design](https://img.shields.io/badge/Material%20Design-757575?logo=materialdesign&logoColor=white)](https://m3.material.io/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
---

## Features

- **Spin & Win wheel** — 10 weighted segments (`1, 2, 3, 5, 10, 15, 20, 30, 50, 100`) with a smooth animated spin and a fixed pointer.
- **Score tracking** — running total, last spin result, and a live spin counter (`x/5`).
- **5-spin Report** — after your 5th spin, unlock a report card that places your total score into one of 7 tiers and highlights the one you landed in:

  |     Tier    | Range |
  |-------------|-------|
  | God Child   | 1–10  |
  | Adipurush   | 11–30 |
  | Normal People | 31–40 |
  | Player Boys | 41–50 |
  | Overthinker | 51–70 |
  | Mck | 71–100 |
  | Abnormal | 101–270 |

- **Homework task list** — add, remove, and randomly pick a task to work on next.
- **Reset** — clears score, spins, and wheel rotation to start a fresh round.

## How the report is calculated

Each spin adds its segment value to your total score. Once 5 spins are complete, your total is matched against the tier table above (ranges are contiguous, so every score from 5–500 lands somewhere). The report also shows a match percentage based on your tier's position out of 7.

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── yourdomain/
│       │           └── wheelofwater/
│       │               ├── MainActivity.kt
│       │               └── WheelView.kt
│       ├── res/
│       │   ├── drawable/
│       │   │   ├── ic_pointer.xml
│       │   │   └── (other icons)
│       │   ├── layout/
│       │   │   ├── activity_main.xml
│       │   │   └── dialog_amount.xml
│       │   ├── values/
│       │   │   ├── colors.xml
│       │   │   ├── strings.xml
│       │   │   └── themes.xml
│       │   └── drawable-v24/
│       │       └── ic_launcher_foreground.xml
│       └── AndroidManifest.xml
├── build.gradle
└── build.gradle
```

## Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

##  Contact

Om Gedam

GitHub: [https://github.com/itsomg134](https://github.com/itsomg134)

Email: [omgedam123098@gmail.com](mailto:omgedam123098@gmail.com)

Twitter (X): [https://twitter.com/omgedam](https://twitter.com/omgedam)

LinkedIn: [https://linkedin.com/in/omgedam](https://linkedin.com/in/omgedam)

Portfolio: [https://ogworks.lovable.app](https://ogworks.lovable.app)


### "There are billions of people on Earth—but only one version of you."
