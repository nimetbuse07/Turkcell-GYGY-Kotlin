package data

import model.ToDo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<ToDo>
    //fun olursa -> fonksiyon bitene kadar thread'i kilitler.
    //UI'da kilitlenme yaşamamak için kullanılır -> suspend
}



