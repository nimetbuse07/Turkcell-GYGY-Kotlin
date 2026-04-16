package main
fun main() {
    val chef: Chef = AssistantChef("Gizli Sos")
    val apprentice: Chef = Apprentice("Gizli Tarif")

    chef.washHands()
    chef.cook()

    println("------")

    apprentice.washHands()
    apprentice.cook()
}