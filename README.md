# Kursyt-Walut
# Kursyt Walut 🌍💶  Aplikacja mobilna na system Android do monitorowania aktualnych kursów walut w czasie rzeczywistym. Projekt wykorzystuje architekturę MVVM i integruje się z zewnętrznym API giełdowym, kładąc szczególny nacisk na bezpieczeństwo danych oraz obsługę błędów sieciowych (tryb offline).
## 🚀 Główne funkcjonalności
* **Pobieranie danych na żywo:** Asynchroniczne pobieranie najnowszych kursów walut z sieci przy użyciu biblioteki Retrofit2.
* **Bezpieczeństwo klucza API:** Zastosowanie `EncryptedSharedPreferences` (Jetpack Security) do szyfrowania i bezpiecznego przechowywania klucza API podanego przez użytkownika.
* **Tryb Offline:** Lokalny cache z wykorzystaniem biblioteki Gson. W przypadku braku dostępu do internetu, aplikacja wczytuje ostatnio zapisane dane i wyświetla odpowiedni komunikat ostrzegawczy.
* **Filtrowanie w locie:** Dynamiczna wyszukiwarka walut reagująca na wpisywany tekst w czasie rzeczywistym.
* **Kalkulacja PLN:** Automatyczne przeliczanie i odwracanie kursów giełdowych, aby wyświetlać rzeczywisty koszt zakupu 1 jednostki obcej waluty w polskich złotych.

## 🛠️ Technologie i Architektura
* **Język:** Kotlin
* **Architektura:** MVVM (Model-View-ViewModel)
* **UI:** Jetpack Compose (z nawigacją Compose Navigation)
* **Sieć:** Retrofit2, Gson
* **Wielowątkowość:** Kotlin Coroutines (Współprogramy)
* **Bezpieczeństwo i Pamięć lokalna:** Jetpack Security (Crypto), SharedPreferences

## 🚧 W planach (To-Do)
- [ ] Dodawanie walut do listy "Ulubionych".
- [ ] Implementacja `WorkManager` do cyklicznego pobierania danych w tle (np. raz dziennie).
- [ ] Rysowanie wykresów historycznych dla wybranych walut.
