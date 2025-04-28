package br.com.lucascordeiro.nexly.shared.network.error

class NetworkException(
    val code: Int,
    override val message: String
) : Exception()