package main
abstract class Chef(private var secretRecipe: String) {

    fun getRecipe(): String {
        return "Bu tarif gizlidir!"
    }

    abstract fun cook()

    fun washHands() {
        println("Aşçı ellerini yıkıyor")
    }
}