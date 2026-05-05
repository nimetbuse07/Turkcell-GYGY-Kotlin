package com.nimetatila.libraryapp.data.repository



import com.nimetatila.libraryapp.data.model.Profile
import com.nimetatila.libraryapp.data.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest

class AuthRepository {

    suspend fun signIn(email: String, password: String): Result<Unit> = runCatching {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signOut() {
        supabase.auth.signOut()
    }
    suspend fun signUp(

        email: String,
        password: String,
        fullName: String,
        studentNo: String?
    ): Result<Unit> = runCatching {
        val authResult = supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }

        val userId = authResult?.id ?: supabase.auth.currentUserOrNull()?.id
        ?: error("Kullanıcı ID alınamadı")

        val newProfile = Profile(

            userId = userId,

            role = "student",

            fullName = fullName,

            studentNo = studentNo

        )
        supabase.postgrest["profiles"].insert(newProfile)
    }

    fun getCurrentUserId(): String? {

        return supabase.auth.currentUserOrNull()?.id

    }
    suspend fun getProfile(userId: String): Profile? {

        return try {

            val response = supabase.postgrest["profiles"]

                .select {

                    filter {

                        eq("user_id", userId)

                    }

                }.decodeSingleOrNull<Profile>()

            println("DEBUG: Veritabanından gelen yanıt: $response")

            response

        } catch (e: Exception) {

            println("DEBUG: getProfile Hatası: ${e.message}")

            e.printStackTrace()

            null

        }

    }

}
