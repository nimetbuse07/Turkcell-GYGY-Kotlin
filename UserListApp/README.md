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

### Success State
<img width="294" height="584" alt="success" src="https://github.com/user-attachments/assets/d3b6d3d7-adc6-45ba-86b6-6b35b5d15a1d" />


### Loading State

<img width="293" height="584" alt="loading" src="https://github.com/user-attachments/assets/6c99ebf7-48e4-4f2a-833e-d01cefe03e7d" />

### Error State

<img width="293" height="584" alt="error" src="https://github.com/user-attachments/assets/bd57b5ec-0a0a-478c-8e20-f85f4f11c39c" />

