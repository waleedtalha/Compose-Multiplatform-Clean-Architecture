package com.app.cmpproject.data.di

import com.app.cmpproject.data.remote.RemoteDataSourceImpl
import com.app.cmpproject.data.remote.RemoteDataSource
import com.app.cmpproject.data.repository.AllUsersRepositoryImpl
import com.app.cmpproject.data.repository.LoginRepositoryImpl
import com.app.cmpproject.domain.repository.AllUsersRepository
import com.app.cmpproject.domain.repository.LoginRepository
import com.app.cmpproject.domain.usecase.AllUsersUseCase
import com.app.cmpproject.domain.usecase.LoginUseCase
import com.app.cmpproject.presentation.screens.login.LoginViewModel
import com.app.cmpproject.presentation.screens.users.AllUsersViewModel
import com.app.cmpproject.util.extensions.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initializeKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            provideHttpClientModule,
            viewModelsModule,
            repositoriesModule,
            useCasesModule,
            dataSourceModule
        )
    }

val useCasesModule = module {
    factory { LoginUseCase(get()) }
    factory { AllUsersUseCase(get()) }
}
val repositoriesModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<AllUsersRepository> { AllUsersRepositoryImpl(get()) }
}
val viewModelsModule = module {
    factory { LoginViewModel(get()) }
    factory { AllUsersViewModel(get()) }
}
val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}
val provideHttpClientModule = module {
    single {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
}
