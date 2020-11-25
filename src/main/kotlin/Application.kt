import core.ownerCoroutineScope
import data.model.Credentials
import data.service.AuthService
import dispatcher.DispatcherProvider
import kotlinx.coroutines.launch

class Application(
    private val dispatchers: DispatcherProvider,
    private val authService: AuthService
) : DispatcherProvider by dispatchers {

    suspend fun run() = ownerCoroutineScope {
        launch(default) {
            val credentials = Credentials("eve.holt@reqres.in", "123456")
            authService.register(credentials).get.also(::println)
        }
    }
}