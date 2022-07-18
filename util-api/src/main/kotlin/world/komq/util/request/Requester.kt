/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.request

import world.komq.util.enums.HeaderType
import world.komq.util.enums.RestMethod
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers
import java.nio.charset.StandardCharsets

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object Requester {
    private val kwDirFile = File("${System.getProperty("user.home")}/.komworld")
    private val komworldDir = if (!kwDirFile.exists()) {
        kwDirFile.mkdirs()
        kwDirFile
    } else kwDirFile

    private val reqConfig = if (!File(komworldDir, "reqConfig.txt").exists()) {
        File(komworldDir, "reqConfig.txt").createNewFile()
        File(komworldDir, "reqConfig.txt")
    }
    else File(komworldDir, "reqConfig.txt")

    val requestHeaderValue = reqConfig.readLines(StandardCharsets.UTF_8)[0].ifBlank { null }
    val mojangBearer = reqConfig.readLines(StandardCharsets.UTF_8)[1].ifBlank { null }

    fun request(url: String, restMethod: RestMethod, headerType: HeaderType, manualHeader: Map<String, String>? = null): String {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder(URI.create(url))

        when (headerType) {
            HeaderType.DEFAULT -> {
                request.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
            }
            HeaderType.MANUAL -> {
                requireNotNull(manualHeader).forEach {
                    request.header(it.key, it.value)
                }
            }
        }

        request.method(restMethod.name, BodyPublishers.ofString(""))

        val response = client.send(request.build(), BodyHandlers.ofString(StandardCharsets.UTF_8))

        return response.body()
    }
}