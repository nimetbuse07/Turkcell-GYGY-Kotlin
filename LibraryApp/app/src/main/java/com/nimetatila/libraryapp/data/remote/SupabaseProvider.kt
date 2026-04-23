package com.nimetatila.libraryapp.data.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseProvider {
    val client = createSupabaseClient(
        supabaseUrl = "https://zudibltrefuwkxkfcutk.supabase.co",
        supabaseKey = "sb_publishable_ZtrWlOWqCNqcSJf_IQVlmQ_3I9-XBdv"
    ) {
        install(Auth)
        install(Postgrest)
    }
}