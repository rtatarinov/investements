import com.investments.httpapi.api.errors.exceptions.ApiException
import com.investments.httpapi.config.CategoryConfig
import com.investments.httpapi.routes.registerCategoriesRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin
import org.jetbrains.exposed.sql.Database

fun main() {
    val envHttpExternalPort = System.getenv("HTTP_EXTERNAL_PORT")
    val httpExternalPort: Int = envHttpExternalPort?.toInt() ?: 8000
    val categoryConfig = CategoryConfig()
    val dbUser = System.getenv("POSTGRES_USER")
    val dbPassword = System.getenv("POSTGRES_PASSWORD")
    val dbName = System.getenv("POSTGRES_DB")
    val dbPort = System.getenv("POSTGRES_EXTERNAL_PORT")

    Database.connect(
        "jdbc:postgresql://localhost:$dbPort/$dbName", driver = "org.postgresql.Driver",
        user = dbUser, password = dbPassword
    )

    embeddedServer(Netty, port = httpExternalPort) {
        install(ContentNegotiation) {
            json()
        }

        install(DefaultHeaders)

        install(CallLogging)

        install(Koin) {
            modules(categoryConfig.getCategoryModule())
            modules(categoryConfig.getCategoryFactory())
            modules(categoryConfig.getCategoryViewFactory())
            modules(categoryConfig.getCategoryModifier())
        }

        install(StatusPages) {
            exception<ApiException> {
                call.respond(it.statusCode(), it.response())
            }

            exception<Throwable> { cause ->
                call.respond(HttpStatusCode.InternalServerError)
                throw cause
            }
        }

        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Patch)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            anyHost()
        }

        install(Compression) {
            gzip()
        }

        registerCategoriesRoutes()
    }.start(wait = true)
}