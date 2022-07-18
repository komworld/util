/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.request

import world.komq.util.LibraryLoader
import world.komq.util.enums.KomworldRestResult
import world.komq.util.enums.RankType
import world.komq.util.enums.RestMethod
import java.util.UUID

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

interface KomworldRestUtil {
    companion object: KomworldRestUtil by LibraryLoader.loadImplement(KomworldRestUtil::class.java)

    /***
     * 번지코드 프록시에서 플레이어를 차단하거나 차단을 해제합니다.
     *
     * @param playerUUID 플레이어의 UUID
     * @param isUnban 플레이어 차단 해제 여부
     * @param reason 차단 사유
     * @return 결과값
     */
    fun banPlayer(playerUUID: UUID, isUnban: Boolean, reason: String? = null): KomworldRestResult

    /***
     * 플레이어의 랭크를 가져오거나 설정합니다.
     *
     * @param playerUUID 플레이어의 UUID
     * @param restMethod REST 방식, GET/PUT만 가능
     * @param rank 랭크 설정시 사용할 랭크
     * @return 랭크를 가져오거나 설정하였을때의 결과값, REST 요청 방식이 잘못되었을 경우 ERROR
     */
    fun rankSettings(playerUUID: UUID, restMethod: RestMethod, rank: RankType? = null): String

    /***
     * 플레이어가 이전에 서버에서 플레이했는지 확인합니다.
     *
     * @param playerUUID 플레이어의 UUID
     * @return 결과값
     */
    fun hasPlayedBefore(playerUUID: UUID): KomworldRestResult
}