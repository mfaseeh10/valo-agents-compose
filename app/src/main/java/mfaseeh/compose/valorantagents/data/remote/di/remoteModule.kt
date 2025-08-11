package mfaseeh.compose.valorantagents.data.remote.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDSImpl
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService
import org.koin.dsl.module

// Define the Koin module
val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single {
        HttpClient(Android) {
            defaultRequest {
                url("https://valorant-api.com/v1/")
            }
            
            install(ContentNegotiation) {
                json(get<Json>())
            }
            
            install(Logging) {
                level = LogLevel.INFO
            }
            
            install(HttpTimeout) {
                requestTimeoutMillis = 20_000
                connectTimeoutMillis = 20_000
                socketTimeoutMillis = 20_000
            }
        }
    }
}

val apiModule = module {
    single {
        ApiService(get<HttpClient>())
    }
}

val valoAgentsRemoteModule = module {
    single<AgentsRDS> {
        AgentsRDSImpl(get())
    }
}
