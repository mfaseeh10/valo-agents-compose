package mfaseeh.compose.valorantagents.data.remote.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDS
import mfaseeh.compose.valorantagents.data.remote.source.AgentsRDSImpl
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .client(get<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
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

// Combine all modules into a list for starting Koin
val appModules = listOf(networkModule, apiModule, valoAgentsRemoteModule)
