package br.com.lucascordeiro.nexly.shared.test.network

import br.com.lucascordeiro.nexly.shared.network.error.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientMock(
    private val mockEngine: MockEngine
) : br.com.lucascordeiro.nexly.shared.network.client.HttpClient {

    override fun invoke() : HttpClient {
        return HttpClient(mockEngine) {
            expectSuccess = true

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response

                    throw NetworkException(
                        code = exceptionResponse.status.value,
                        message = exceptionResponse.body()
                    )
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}