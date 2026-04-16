package main
class AssistantChef(secretRecipe: String) : Chef(secretRecipe) {

    fun helpChef() {
        println("Yardımcı aşçı şefe yardım ediyor")
    }

    override fun cook() {
    println("Yardımcı aşçı daha basit yemek yapıyor")
}

}