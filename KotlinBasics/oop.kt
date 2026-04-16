package com.example.kotlin
//import -> başka bir package içeriğini tanımak için kullanılır
import com.example.kotlin.models.Car

fun main() {
    var car1: Car = Car() //Car türünde bir nesne oluşturduk. 
    //Classlardan üretilen her bir örneğe instance denir. Car sınıfından üretilen her bir nesne bir instance'dır.
    car1.brand = "Toyota"
    car1.model = "Corolla"
    car1.year = 2020
    car1.rent()
    println(car1)

    //O kalıptan türeyen örnekler
    var car2: Car = Car()
    car2.brand = "Honda"
    car2.model = "Civic"
    car2.year = 2019
    car2.rent()
    println(car2)

    //Veritabanı işlemleri class
    //İş kodları, validasyon yapan class
    //Bir nesneti temsil eden classlar 


    println(car1) //Car@6bc7c054 => Car türünde bir nesne oluşturduk ve bellekteki adresini yazdırdık.
    var name: String = "Can"
    println("merhaba $name") //merhaba Can => String türünde bir değişken oluşturduk ve içine "Can" değerini atadık, sonra da bu değeri ekrana yazdırdık.

}