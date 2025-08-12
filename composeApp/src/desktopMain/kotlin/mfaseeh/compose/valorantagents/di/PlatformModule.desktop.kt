package mfaseeh.compose.valorantagents.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mfaseeh.compose.valorantagents.data.local.database.buildValoAgentDatabase
import mfaseeh.compose.valorantagents.data.local.database.ValoAgentDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single {
        HttpClient(OkHttp) {
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
    
    single<ValoAgentDatabase> {
        buildValoAgentDatabase()
    }
    
    factory {
        val database: ValoAgentDatabase = get()
        database.agentDao()
    }
}
