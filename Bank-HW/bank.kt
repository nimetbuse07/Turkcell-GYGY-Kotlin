var hesapBakiyesi = 1000.0
var musteriSifre = "1234"
var gecmisHareketler = mutableListOf<String>()

fun main() {
    println("Bankamıza hoş geldiniz!")
    girisYap()
}

fun girisYap() {
    var girisHakkı = 3
    while (girisHakkı > 0) {
        println("Lütfen şifrenizi giriniz:")
        val sifre = readLine() ?: ""
        if (sifre == musteriSifre) {
            println("Giriş başarılı")
            menu()
            return
        } else {
            girisHakkı--
            println("Hatalı şifre! Kalan hak: $girisHakkı")
        }
    }
    println("Kartınız bloke edildi!")
}

fun bakiyeSorgula() {
    println("Hesap bakiyeniz: $hesapBakiyesi TL")
    gecmisHareketler.add("Bakiye sorgulandı: $hesapBakiyesi TL")
}

fun paraYatir(miktar: Double) {
    if (miktar > 0) {
        hesapBakiyesi += miktar
        println("$miktar TL yatırıldı. Yeni bakiye: $hesapBakiyesi TL")
        gecmisHareketler.add("Para yatırıldı: $miktar TL, Yeni bakiye: $hesapBakiyesi TL")
    } else {
        println("Geçersiz miktar")
    }
}

fun paraCek(miktar: Double) {
    if (miktar > 0 && miktar <= hesapBakiyesi) {
        hesapBakiyesi -= miktar
        println("$miktar TL çekildi. Yeni bakiye: $hesapBakiyesi TL")
        gecmisHareketler.add("Para çekildi: $miktar TL, Yeni bakiye: $hesapBakiyesi TL")
    } else {
        println("Yetersiz bakiye veya geçersiz miktar")
    }
}

fun gecmisHareketleriGoster() {
    println("\nGeçmiş hareketler:")
    if (gecmisHareketler.isEmpty()) {
        println("Henüz işlem yok.")
    } else {
        gecmisHareketler.forEachIndexed { index, hareket ->
            println("${index + 1}. $hareket")
        }
    }
}

fun krediKartiBasvurusu() {
    println("Kredi kartı başvurusu yapıldı. En kısa sürede sonuçlanacaktır.")
    gecmisHareketler.add("Kredi kartı başvurusu yapıldı")
}

fun menu() {
    var devam = true
    while (devam) {
        println("\n--- ANA MENÜ ---")
        println("1. Bakiye Sorgula")
        println("2. Para Yatır")
        println("3. Para Çek")
        println("4. Geçmiş Hareketler")
        println("5. Kredi Kartı Başvurusu")
        println("6. Çıkış")

        print("Seçiminiz: ")
        val secim = readLine() ?: ""

        when (secim) {
            "1" -> bakiyeSorgula()
            "2" -> {
                println("Yatırmak istediğiniz miktarı giriniz:")
                val miktar = readLine()?.toDoubleOrNull() ?: 0.0
                paraYatir(miktar)
            }
            "3" -> {
                println("Çekmek istediğiniz miktarı giriniz:")
                val miktar = readLine()?.toDoubleOrNull() ?: 0.0
                paraCek(miktar)
            }
            "4" -> gecmisHareketleriGoster()
            "5" -> krediKartiBasvurusu()
            "6" -> {
                println("Çıkış yapılıyor... İyi günler!")
                devam = false
            }
            else -> println("Geçersiz seçim")
        }
    }
}