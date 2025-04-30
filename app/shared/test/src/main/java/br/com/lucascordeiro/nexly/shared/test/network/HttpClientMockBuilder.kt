package br.com.lucascordeiro.nexly.shared.test.network

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HttpClientMockBuilder {
    private var dispatcher: CoroutineDispatcher = Dispatchers.Default
    private var handler: HttpRequestHandler = object : HttpRequestHandler {
        override fun handle(request: HttpRequestData): ResponseData {
            println("default handler")
            return ResponseData(
                content = "",
                status = HttpStatusCode.BadRequest
            )
        }
    }

    private fun MockRequestHandleScope.handleRequest(
        request: HttpRequestData
    ): HttpResponseData {
        val response = handler.handle(request)

        return respond(
            content = response.content,
            status = response.status,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    fun setDispatcher(dispatcher: CoroutineDispatcher) : HttpClientMockBuilder {
        this.dispatcher = dispatcher
        return this
    }

    fun setHandler(handler: HttpRequestHandler) : HttpClientMockBuilder {
        this.handler = handler
        return this
    }

    fun build() : HttpClientMock {
        val config = MockEngineConfig()
        config.addHandler { request -> handleRequest(request) }
        config.dispatcher = dispatcher

        val mockEngine = MockEngine(config)

        return HttpClientMock(mockEngine)
    }
}