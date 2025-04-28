package br.com.lucascordeiro.nexly.shared.network.client

import io.ktor.client.HttpClient

interface HttpClient {
    operator fun invoke(): HttpClient
}