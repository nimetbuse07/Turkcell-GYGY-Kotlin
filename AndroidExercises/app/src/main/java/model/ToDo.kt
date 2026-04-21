package model

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    val id: Int = 0,
    val title: