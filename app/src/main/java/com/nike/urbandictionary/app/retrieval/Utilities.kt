package com.nike.urbandictionary.app.retrieval

import android.accounts.NetworkErrorException
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Throws(IOException::class)
fun getHttpsRequest(url: String, timeout: Int,
                    request: String, headers: List<List<String>> = emptyList()) : HttpsURLConnection? =
    (URL(url).openConnection() as? HttpsURLConnection)?.run {
        readTimeout = timeout
        connectTimeout = timeout
        requestMethod = request
        headers.forEach { header ->
            setRequestProperty(header[0], header[1])
        }
        connect()
        if (responseCode != HttpsURLConnection.HTTP_OK)
            throw NetworkErrorException()
        this
    } ?: throw IOException()