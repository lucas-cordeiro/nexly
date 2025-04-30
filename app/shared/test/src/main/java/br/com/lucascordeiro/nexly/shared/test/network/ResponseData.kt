package br.com.lucascordeiro.nexly.shared.test.network

import io.ktor.http.HttpStatusCode

data class ResponseData(
    val status: HttpStatusCode,
    val content: String
)