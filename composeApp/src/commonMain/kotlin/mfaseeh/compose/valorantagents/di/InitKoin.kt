package mfaseeh.compose.valorantagents.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    
    // Include all modules now that everything is in one place
    modules(
        networkModule,
        platformModule,
        apiModule,
        remoteModule,
        localModule,
        repositoryModule,
        useCaseModule,
        appModule
    )
}
