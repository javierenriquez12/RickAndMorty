package com.zara.data.entity.error

class RequestException(val code: Int, message: String) : Throwable(message)
