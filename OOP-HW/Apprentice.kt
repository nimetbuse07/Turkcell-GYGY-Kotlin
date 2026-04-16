package main
class Apprentice(secretRecipe: String) : Chef(secretRecipe) {

    fun learn() {
        println("Çırak öğreniyor")
    }

    override fun cook() {
    println("Çırak yemek yapmayı öğreniyor")
}
}