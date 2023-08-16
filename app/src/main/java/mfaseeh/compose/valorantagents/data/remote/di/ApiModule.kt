package mfaseeh.compose.valorantagents.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mfaseeh.compose.valorantagents.data.remote.source.api.ApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideValorantApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}