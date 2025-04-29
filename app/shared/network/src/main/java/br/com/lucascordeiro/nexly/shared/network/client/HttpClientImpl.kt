package br.com.lucascordeiro.nexly.shared.network.client

import br.com.lucascordeiro.nexly.shared.network.BuildConfig
import br.com.lucascordeiro.nexly.shared.network.error.NetworkException
import br.com.lucascordeiro.nexly.shared.network.error.NoInternetConnectionException
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal data class HttpClientImpl(
    private val httpUrl: HttpUrl,
    private val httpLogger: Logger
) : HttpClient {
    override operator fun invoke(): io.ktor.client.HttpClient {
        return io.ktor.client.HttpClient(OkHttp) {
            expectSuccess = true

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    if (exception is UnknownHostException) {
                        throw NoInternetConnectionException()
                    }

                    if (exception is SocketTimeoutException) {
                        throw NetworkException(
                            code = 408,
                            message = "Request timed out"
                        )
                    }

                    val clientException = exception as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response

                    throw NetworkException(
                        code = exceptionResponse.status.value,
                        message = exceptionResponse.body()
                    )
                }
            }

            install(Logging) {
                logger = httpLogger
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }

            defaultRequest {
                url {
                    protocol = httpUrl.protocol
                    host = httpUrl.host
                }
                header("X-CoinAPI-Key", BuildConfig.CoinApiKey)
            }
        }
    }

    companion object {
        fun default(): HttpClient = HttpClientImpl(
            httpUrl = HttpUrl.default(),
            httpLogger = HttpLogger.default()
        )
    }
}
