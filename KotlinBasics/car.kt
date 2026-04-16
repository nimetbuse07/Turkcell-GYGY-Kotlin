package com.example.kotlin.models
//her dosya bir paketin altında tanımlanır.

//bu şekilde tanımlama => class içi değişkene otomatik olarak atanır. => primary constructor
class Car (var brand: String, var model: String, private var year: Int) {
    //içi boş bir yeni tür oluşturduk.
    //Property -> Özellikler => classların içinde tanımlanan değişkenlerdir.
    //var brand: String = ""
    //var model: String = ""
    //var year: Int = 0
    init {
        println("Car sınıfından bir nesne oluşturuldu") //init bloğu => constructor çalıştığında çalışır.
    }

    fun rent() {
        println("$brand $model kiralandı") //classın fonksiyonu => classın alanlarına erişebiliriz.
    }

    fun getYear() : Int {
        return year
    }
    
    fun setYear(year : Int){
        if(year < 1990 || year > 2024){
            println("Geçersiz yıl")
            return
        }
        //this => Car sınıfının kendisi 
        //Car sınıfının içindeki year değişkeni ile parametrenin değişkenini birbirine eşitliyoruz.
        this.year = year
    }
}

//bu şekilde tanımlama => class içi değişken manuel olarak atanır. => secondary constructor
class Car2 {
    var brand: String = ""
    var model: String = ""
    var year: Int = 0
    
    constructor(brand: String, model: String, year: Int) {
        this.brand = brand
        this.model = model
        this.year = year
        println("Car2 sınıfından bir nesne oluşturuldu")
    }

    fun rent() {
        println("$brand $model $year kiralandı")
    }

}


fun main () {
    var car1: Car = Car("Toyota", "Corolla", 2020) //sanki bir fonksiyon çağırıyor gibi
    car1.rent() //car1 nesnesinin rent fonksiyonunu çağırdık.
    //constructor => yapıcı fonksiyon => classın bir örneğini oluşturmak için kullanılır.
    println(car1.brand) //get => car1.brand'i okudum
    car1.brand = "a"
    // car1.year = 2036 => private olduğu için okuyamam
    println(car1.brand) //set => car1.brand'i a yaptım
    car1.setYear(2023)
    println(car1.getYear()) //getYear fonksiyonunu çağırarak year'ı okuyabilirim.

    var car2 : Car2 = Car2("Honda", "Civic", 2019)
    car2.rent()
}
