fun main(){
    println("Fonksiyonlar")
    selamla()
    selamla2("Banu")
    var not1 = notHesapla(85, "Ali")
    var not2 = notHesapla(45, "Ayşe")
    var not3 = notHesapla(120)
    var not4 = notHesapla(25)

    //named arguments => parametre isimlerini kullanarak fonksiyon çağırmak
    var not5 = notHesapla(not=75, isim="Ege")
    var not6 = notHesapla(isim="Can", not=55)
    
    println(not1)
    println(not2)
    println(not3)
    println(not4)
    println(not5)
    println(not6)

}
//Fonksiyonlar => Bir işi yapan ve tekrar tekrar kullanılabilen kod bloklarıdır.
//Fonksiyon : isimlendirilmiş kod bloklarıdır. 
//Bir fonksiyon tanımlarken, fonksiyonun ne işe yaradığını ve hangi parametreleri alacağını belirtiriz. 
//Fonksiyonlar, kodunuzu daha düzenli ve okunabilir hale getirir.

fun selamla(){
    println("Merhaba")
}
fun selamla2(isim : String){
    println("Merhaba $isim")
}

fun notHesapla(not : Int, isim: String="Öğrenci") : String{
    return when(not){
        in 90..100 -> "$isim,AA"
        in 80..89 -> "$isim,BA"
        in 70..79 -> "$isim,BB"
        in 60..69 -> "$isim,CB"
        in 50..59 -> "$isim,CC"
        in 40..49 -> "$isim,DC"
        in 30..39 -> "$isim,DD"
        in 0..29 -> "$isim,FF"
        else -> "$isim,geçersiz not"

    }}
