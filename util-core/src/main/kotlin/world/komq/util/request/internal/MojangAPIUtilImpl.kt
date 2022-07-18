/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.request.internal

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import world.komq.util.request.MojangAPIUtil
import world.komq.util.request.Requester.mojangBearer
import world.komq.util.request.Requester.request
import world.komq.util.enums.HeaderType
import world.komq.util.enums.NameAvailability
import world.komq.util.enums.RestMethod
import world.komq.util.objects.UtilObject.server
import java.util.*

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

@Suppress("UNUSED")
class MojangAPIUtilImpl: MojangAPIUtil {
    private val defaultSkinData = ""
    private val administrators = arrayListOf(
        ""
    )

    override fun isNameAvailable(requestName: String): NameAvailability {
        if (mojangBearer == null) return NameAvailability.ERROR
        else if (adminNameList().contains(requestName)) return NameAvailability.DUPLICATE

        val reqHeaderMap = mapOf(Pair("Authorization", "Bearer $mojangBearer"))
        val requestValue = request("https://api.minecraftservices.com/minecraft/profile/name/${requestName}/available", RestMethod.GET, HeaderType.MANUAL, reqHeaderMap)

        return if (requestName.length !in 3..16) NameAvailability.ERROR else NameAvailability.valueOf(Json.parseToJsonElement(requestValue).jsonObject["status"].toString().removeSurrounding("\""))
    }

    override fun getSkinData(playerName: String): String {
        val requestValue = request("https://sessionserver.mojang.com/session/minecraft/profile/${server.getPlayerUniqueId(playerName)}", RestMethod.GET, HeaderType.DEFAULT)
        val resultJson = Json.parseToJsonElement(requestValue)

        return if (resultJson.jsonObject.contains("errorMessage")) {
            defaultSkinData
        }
        else {
            resultJson.jsonObject["properties"]?.jsonObject?.get("value").toString()
        }
    }

    private fun adminNameList(): ArrayList<String> {
        val nameList = ArrayList<String>()

        administrators.forEach {
            val requestValue = Json.parseToJsonElement(request("https://api.mojang.com/user/profiles/${it}/names", RestMethod.GET, HeaderType.DEFAULT))

            nameList.add(requestValue.jsonArray[requestValue.jsonArray.lastIndex].jsonObject["name"].toString().removeSurrounding("\""))
        }

        return nameList
    }
}