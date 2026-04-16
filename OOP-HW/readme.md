# Nesne Tabanlı Programlama (OOP) – Kotlin ile

Bu projede, programlama öğrenirken en kritik konulardan biri olan Nesne Tabanlı Programlama (Object Oriented Programming - OOP) konusunu ele alıyoruz.
Nesne Tabanlı Programlama (OOP), yazılım geliştirme sürecinde kodu fonksiyonlar etrafında değil, nesneler (objects) etrafında organize eden bir yaklaşımdır. Bu sayede gerçek dünyadaki varlıkları yazılım ortamına daha anlaşılır ve yönetilebilir şekilde aktarabiliriz.

Bu projede anlatımlar ve örnekler Kotlin programlama dili kullanılarak yapılacaktır. Profesyonel bir aşçı sistemi üzerinden kavramları tek bir senaryo üzerinden inceleyeceğiz.

Bu sistemde:

- Bir baş aşçı (Chef) vardır
- Bir yardımcı aşçı (AssistantChef) vardır
- Bir çırak (Apprentice) vardır
- Aşçının gizli tarifleri vardır
- Aşçı, bilgilerini miras bırakır
- Aşçı farklı şekillerde yemek yapabilir (çok yetenekli)

## OOP Nedir?

OOP, temel olarak iki yapı üzerine kuruludur:

- **Sınıf (Class)**: Nesnelerin şablonudur.
- **Nesne (Object)**: Bu şablondan oluşturulan somut örneklerdir

**Örneğin:**

- Chef [Chef.kt](Chef.kt) bir sınıftır. 
- AssistantChef [Asistantchef.kt](AssistantChef.kt), Apprentice [Apprentice.kt](Apprentice.kt) ise bu sınıftan oluşturulmuş nesnelerdir

## OOP'nin Avantajları

OOP yaklaşımı bize birçok avantaj sağlar:

- **Yeniden Kullanılabilirlik (Reusability):**
Bir kez yazılan kod farklı yerlerde tekrar kullanılabilir.
- **Modülerlik (Modularity):**
Kod küçük parçalara bölünerek daha yönetilebilir hale gelir.
- **Bakım Kolaylığı (Maintainability):**
Kod düzenli olduğu için hata bulmak ve geliştirmek daha kolaydır.

## OOP’nin 4 Temel Prensibi

Bu dokümanda OOP’nin en önemli 4 prensibini inceleyeceğiz:

- **Encapsulation (Kapsülleme)**
- **Inheritance (Kalıtım)**
- **Polymorphism (Çok Biçimlilik)**
- **Abstraction (Soyutlama)**
- **Encapsulation (Kapsülleme)**

### **Encapsulation (Kapsülleme)**
Encapsulation, bir sınıf içerisindeki verilerin doğrudan erişime kapatılması ve sadece belirli yöntemler aracılığıyla erişilmesini sağlar.

**Amaç:**
Veriyi korumak
Kontrolsüz erişimi engellemek


### **Inheritance (Kalıtım)**

Inheritance, bir sınıfın başka bir sınıfın özelliklerini ve davranışlarını miras almasını sağlar.

>  AssistantChef [Asistantchef.kt](AssistantChef.kt) ve  Apprentice [Apprentice.kt](Apprentice.kt) , Chef [Chef.kt](Chef.kt) sınıfından türetilmiştir.

**Amaç:**
Kod tekrarını azaltmak
Ortak özellikleri tek bir yerde toplamak


### **Polymorphism (Çok Biçimlilik)**

Polymorphism, aynı fonksiyonun farklı nesnelerde farklı davranışlar göstermesidir.

> cook() metodu farklı sınıflarda farklı davranır.
> - [Chef.kt](Chef.kt) 8.satır 
> - [Assistantchef.kt](AssistantChef.kt) 8.satır
> - [Apprentice](Apprentice.kt) 8.satır


**Amaç:**
Daha esnek ve genişletilebilir sistemler kurmak



### **Abstraction (Soyutlama)**

Abstraction, gereksiz detayları gizleyerek kullanıcıya sadece gerekli olan bilgiyi sunar.

> [Chef.kt](Chef.kt) dosyasındaki chef sınıfı abstract olarak tanımlanmıştır.

**Amaç:**
Karmaşıklığı azaltmak
Kullanımı kolaylaştırmak
