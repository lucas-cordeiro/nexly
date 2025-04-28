package br.com.lucascordeiro.nexly.shared.network.client

import io.ktor.http.URLProtocol

data class HttpUrl(
    val protocol: URLProtocol,
    val host: String
) {
    companion object {
        fun default() = HttpUrl(
            protocol = URLProtocol.HTTPS,
            host = "rest.coinapi.io"
        )
    }
}
