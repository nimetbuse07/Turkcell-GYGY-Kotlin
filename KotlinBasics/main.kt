// Kotlin -> Java'nın moderni
//Daha az kodla aynı işi yapar

fun main() {
    //scope -> kapsam alanı
    println("hello kotlin")

    /* Değişkenler : Değişkenler, program içerisinde veriyi saklamak için kullanılan,
     belirli bir isimle tanımlanan ve bellekte yer kaplayan yapılardır.
     Bu değişkenlere isimleri aracılığıyla erişilir ve Kotlin’de val ve var olmak üzere ikiye ayrılır.*/

     var a : Int = 5
     println(a)

     // int -> integer = tam sayı

     var name = "Banu" // String(metinsel
     println(name)

     var name2 : String = "Carla"
     println(name2)
     val b : Int = 40 //immutable
     a = 10
    println(a)
    println(b)

// Null Safety = Varsayılan değer non-nullable'dır. Yani null değer atanamaz.
// ? = bu alan null olabilir
var surname : String? = "Jackson"
surname = null
println(surname)
println(surname?.length) //Güvenli kullanım(null'sa yine null döner)
//println(surname!!.length) // kullanıma zorlamak(NullPointerException riski)

var nameSurname : String = "$name $surname" //String interpolation (Değişkenleri string içinde kullanmak)
println(nameSurname)

val age = 25
println("Kullanıcı : $name, 5 yıl sonra ${age + 5} olur")

//Array => Diziler
val numbers: Array<Int> = arrayOf(1,2,3,4,5)

val students : Array<String> = arrayOf("Can","Ali","Ege")

println(numbers[0])
println(students[2])
students[2]="Dogru"
println(students[2])

//Listeler = Arraylere göre daha esnek ve gelişmiş yapılardır

//liste@456 => 1,2,3,4,5
val numberList = listOf(1,2,3,4,5) //immutable list (değiştirilemez)

//liste@123 => 1,2,3,4,5
val numberListMutable = mutableListOf(1,2,3,4,5) //mutable (değiştirilebilir)
numberListMutable.add(6)

//Referans değişir, neden = yeni liste oluşur
//numberListMutable = mutableListOf(7,8,9) //yeni bir liste ataması yapabiliriz

//Control Flow
println(20+30)

println(10 == 20)

val yas : Int = 20
if(yas > 18){
    println("yetki verildi.")
}else if(yas == 18){
    println("ay kotrolü yapılıyor")
}else{
    println("yetki verilmedi")
}

//when(switch case)
val gun : Int = 3 // örnek olarak 3 verdim, değiştirebilirsiniz
when(gun){
    1 -> println("Pazartesi")
    2 -> println("Salı")
    3 -> println("Çarşamba")
    else -> println("geçersiz gün")
}

when(gun){
    1,2,3,4,5-> println("Hafta içi")
    6,7 -> println("Hafta sonu")
    else -> println("geçersiz gün")
}

// Koşullar aynı zamanda birer expressiondur
val sonuc : String = if(yas > 18) "Yetki verildi" else "Yetki verilmedi"

val gunIsmi : String = when(gun){
    1 -> "Pazartesi"
    2 -> "Salı"
    3 -> "Çarşamba"
    else -> "geçersiz gün"
}

//İterasyon = Tekrar eden işlemler için kullanılır.
// aynı scope'taki kodu X kadar tekrar etmek

//for döngüsü
//döngü koşulu sağlandığı sürece döngü bloğundaki kodu tekrar eder
for(i in 1..5){ // i 0'dan başlayarak 5'e kadar (5 dahil) devam eder.
    println(i)
}
for(i in 0..20 step 2){
    println(i)
}
for(i in 10 downTo 0){
    println(i)
}
for(i in 0 until 5){
    println(i)
}

val studentList = listOf("Can","Ali","Ege","Efe","Deniz")
for (student in studentList) 
{
    if(student == "Ege")
        continue //bbu öğrenciyi atla diğerleirne devam et
    println(student)
}
println("***********************")
for (student in studentList) 
{
    if(student == "Ege")
        break //bu öğrenciyi gördüğünde döngüyü tamamen bitir
    println(student)
}

for (i in 1..3){
    for(j in 1..3){
        println("$i , $j")
    }
}
}

