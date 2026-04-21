package data

import model.ToDo

class TodoRepository {
    suspend fun getTodos(): List<ToDo>{
        return RetrofitClient.api.getTodos()
    }
}