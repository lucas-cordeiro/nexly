package br.com.lucascordeiro.nexly.shared.test.network

import br.com.lucascordeiro.nexly.shared.network.error.NetworkException
import io.ktor.http.HttpStatusCode

fun errorResponse() : ResponseData {
    return ResponseData(
        status = HttpStatusCode.BadRequest,
        content = ""
    )
}

fun successResponse(content: String) : ResponseData {
    return ResponseData(
        status = HttpStatusCode.OK,
        content = content
    )
}

suspend fun handleErrorResponse(
   request: suspend () -> Unit
) : NetworkException? {
    return try {
        request()
        null
    } catch (e: NetworkException) {
        e
    }
}

fun ResponseData.toNetworkException() : NetworkException {
    return NetworkException(
        code = status.value,
        message = content
    )
}