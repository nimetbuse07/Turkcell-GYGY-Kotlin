# UserListApp

Modern Android uygulaması.  
JSONPlaceholder API kullanılarak kullanıcı listesi çekilir ve Jetpack Compose ile gösterilir.

---

## Özellikler

- Kullanıcı listesini API üzerinden çekme
- MVVM mimarisi
- StateFlow ile reaktif UI yönetimi
- Kullanıcı arama (isim / email filtreleme)
- Kullanıcı detay ekranı (Navigation Compose)
- Material 3 UI tasarımı
- Dark Mode desteği

---

## Kullanılan Teknolojiler

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Retrofit
- Gson Converter
- Coroutines
- StateFlow
- Navigation Compose
- Material 3

---

## API

Kullanılan API: https://jsonplaceholder.typicode.com/users

---

---

## Kurulum

1. Projeyi klonla:
```bash
git clone <https://github.com/nimetbuse07/Turkcell-GYGY-Kotlin/tree/main/UserListApp>
```
2. Android Studio ile aç
3. Gradle sync tamamlanmasını bekle
4. Run ile çalıştır

## Mimari

- Proje MVVM (Model - View - ViewModel) mimarisi ile geliştirilmiştir:
- Model: API veri modelleri
- Repository: Veri kaynağı yönetimi
- ViewModel: UI state yönetimi (StateFlow)
- UI: Jetpack Compose ekranları

## Ekran Görüntüleri

### Loading State
![Loading Screen](screenshots/loading.png)

### Success State
![Success Screen](screenshots/success.png)

### Error State
![Error Screen](screenshots/error.png)