import data.api.AuthApi
import data.service.AuthService
import data.service.AuthServiceImpl
import dispatcher.DefaultDispatcherProvider
import dispatcher.DispatcherProvider
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main() {
    val httpClient = createHttpClient()
    val authService = createAuthService(httpClient)
    val dispatcherProvider = createCoroutineDispatchers()

    val application = createApplication(
        dispatcherProvider = dispatcherProvider,
        authServiceImpl = authService,
    )

    runBlocking { application.run() }

    httpClient.close()
}

private fun createHttpClient() =
    HttpClient(CIO) {
        defaultRequest {
            contentType(ContentType.Application.Json)
            url.protocol = URLProtocol.HTTPS
            url.host = "reqres.in"
        }

        install(Logging) { logger = Logger.DEFAULT; level = LogLevel.INFO }
        install(JsonFeature) { serializer = defaultSerializer() }

        engine { threadsCount = 6 }
    }

private fun createAuthService(httpClient: HttpClient) =
    AuthServiceImpl(AuthApi(httpClient))

private fun createCoroutineDispatchers() =
    DefaultDispatcherProvider(
        default = Dispatchers.Default,
        io = Dispatchers.IO
    )

private fun createApplication(
    dispatcherProvider: DispatcherProvider,
    authServiceImpl: AuthService,
) = Application(
    dispatchers = dispatcherProvider,
    authService = authServiceImpl
)
