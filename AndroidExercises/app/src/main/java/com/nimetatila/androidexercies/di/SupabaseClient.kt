package com.nimetatila.androidexercies.di

import android.net.http.HttpResponseCache.install
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

// Supabase bağlantısını tek bir yerde yönetmek için singleton object
object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://xyxxrztcliuusiwtfylr.supabase.co",
        supabaseKey = "sb_publishable_VVc0GMjk6P0NE3HvTPOnBg_tc5mycdi"
    ) {
        install(Postgrest)
    }
}