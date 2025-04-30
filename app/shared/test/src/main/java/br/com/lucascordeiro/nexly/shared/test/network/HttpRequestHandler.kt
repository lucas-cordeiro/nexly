package br.com.lucascordeiro.nexly.shared.test.network

import io.ktor.client.request.HttpRequestData

interface HttpRequestHandler {
    fun handle(request: HttpRequestData): ResponseData
}