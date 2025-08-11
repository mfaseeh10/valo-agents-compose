package mfaseeh.compose.valorantagents.data.remote.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDSImpl
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

// Define the Koin module
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .client(get<OkHttpClient>())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }
}

val apiModule = module {
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}

val valoAgentsRemoteModule = module {
    single<AgentsRDS> {
        AgentsRDSImpl(get())
    }
}
