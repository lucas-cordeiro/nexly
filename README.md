# 📱 Nexly

**Nexly** is a minimalist mobile app built with Kotlin and Jetpack Compose that allows users to explore and view information about cryptocurrency exchanges using the [CoinAPI](https://www.coinapi.io/).
---

## 🚀 Features

- 📃 **Exchange List:** Displays a clean list of exchanges with name, ID, and 24h USD volume.
- 🔍 **Details Screen:** Shows detailed data including:
  - Exchange name, ID
  - Volume over 1 hour, 1 day, and 1 month
  - Rank
  - Historical activity periods (quotes, order book, trades)
  - Website
- 💡 **Modern UI:** Built using Jetpack Compose with Material 3 and Clean Architecture.
---

## 📸 Screenshots

Below are sample screens of the app using the Cryptooly-inspired layout:

|                                           Exchange List                                           |                                          Exchange Details                                           |
|:-------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------:|
| ![Exchange List](https://github.com/user-attachments/assets/1b27b884-7967-4c35-8919-fd118c96d0cc) | ![Exchange Detail](https://github.com/user-attachments/assets/3fe568eb-d3c3-41a5-80a2-f5e8c673c9ce) |

---

## 🖼️ UI Inspiration

This project is inspired by the [Cryptooly – Crypto Wallet Mobile UI](https://www.figma.com/community/file/967630197799352896/cryptooly-crypto-wallet-mobile-ui-free) design. The UI aims to be clean, modern, and user-friendly, focusing on essential information presentation.

---

## 📦 Tech Stack

- **Kotlin** `2.1.20`
- **Jetpack Compose** `2025.04.01`
- [**Koin**](https://insert-koin.io/) `4.0.4`
- [**Ktor Client**](https://ktor.io/) (with kotlinx.serialization) `3.1.2`
- **Material 3**
- **MVVM + Clean Architecture**
- **CoinAPI integration**

---

## 🧪 Testing

The project includes both **unit tests** and **UI tests** to ensure code quality and stability.

### ✅ Unit Tests

- Written using **JUnit 4**
- Mocks created with [**MockK**](https://mockk.io/ANDROID.html)
- Testable components include:
  - ViewModels with mocked HttpClient

Run unit tests with:

```bash
./gradlew testDebugUnitTest
```

### ✅ UI Tests

- Written using **Compose Test**
- Includes tests for:
  - UI Screens with mocked ViewModels

Run UI tests with:

```bash
./gradlew connectedDebugAndroidTest
```

## 🛠 Getting Started

Follow these steps to set up and run the project locally.

### 🖥 Tested on

| Tool               | Version   |
|--------------------|-----------|
| **Android Studio** | ✅ Meerkat |
| **Java SDK**       | ✅ 21.0.6  |
| **Gradle**         | ✅ 8.13    |
| **Kotlin**         | ✅ 2.1.20  |

---

### 📥 Clone the repository

```bash
git clone https://github.com/lucas-cordeiro/nexly.git
cd nexly
```

### 🔑 CoinAPI Key Configuration

To use the app, you need a valid [CoinAPI](https://www.coinapi.io/) key.

1. Go to [https://www.coinapi.io/](https://www.coinapi.io/) and create an account to get your API key.
2. In the root the project, locate or create the `local.properties` file.
3. Add the following line:
```bash
CoinApiKey=your_api_key_here
```
