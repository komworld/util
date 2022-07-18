/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.request.internal

import world.komq.util.request.KomworldRestUtil
import world.komq.util.request.Requester.request
import world.komq.util.request.Requester.requestHeaderValue
import world.komq.util.enums.HeaderType
import world.komq.util.enums.KomworldRestResult
import world.komq.util.enums.RankType
import world.komq.util.enums.RestMethod
import java.util.*

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

@Suppress("UNUSED")
class KomworldRestUtilImpl: KomworldRestUtil {
    private val requestURL = "https://api.komq.world/rest"
    private val reqHeaderMap = mapOf(Pair("Authorization", requireNotNull(requestHeaderValue)))

    override fun banPlayer(playerUUID: UUID, isUnban: Boolean, reason: String?): KomworldRestResult {
        val request = request("${requestURL}/bungee/ban/?uuid=${playerUUID}/&isUnban=$isUnban${ if (reason != null) "&reason=${reason.replace(" ", "%20")}" else "" }", RestMethod.PUT, HeaderType.MANUAL, reqHeaderMap)

        return KomworldRestResult.valueOf(request)
    }

    override fun hasPlayedBefore(playerUUID: UUID): KomworldRestResult {
        val request = request("${requestURL}/paper/hasPlayedBefore/?uuid=${playerUUID}", RestMethod.GET, HeaderType.MANUAL, reqHeaderMap)

        return KomworldRestResult.valueOf(request)
    }

    override fun rankSettings(playerUUID: UUID, restMethod: RestMethod, rank: RankType?): String {
        val request = when (restMethod) {
            RestMethod.GET -> request("${requestURL}/paper/rank/?uuid=${playerUUID}", restMethod, HeaderType.MANUAL, reqHeaderMap)
            RestMethod.PUT -> {
                request("${requestURL}/paper/rank/?uuid=${playerUUID}&rank=${requireNotNull(rank)}", restMethod, HeaderType.MANUAL, reqHeaderMap)
            }
            else -> return KomworldRestResult.ERROR.name
        }

        return request
    }
}