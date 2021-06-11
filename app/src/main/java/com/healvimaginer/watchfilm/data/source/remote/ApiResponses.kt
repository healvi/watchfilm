package com.healvimaginer.watchfilm.data.source.remote

class ApiResponses<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponses<T> = ApiResponses(StatusResponse.SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponses<T> = ApiResponses(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponses<T> = ApiResponses(StatusResponse.ERROR, body, msg)
    }
}