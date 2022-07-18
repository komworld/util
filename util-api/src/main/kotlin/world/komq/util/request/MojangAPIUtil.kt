/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.request

import world.komq.util.LibraryLoader
import world.komq.util.enums.NameAvailability
import java.util.*

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

interface MojangAPIUtil {
    companion object: MojangAPIUtil by LibraryLoader.loadImplement(MojangAPIUtil::class.java)

    /***
     * 플레이어의 UUID로 스킨 Base64 인코드 문자열을 가져옵니다.
     *
     * @param playerName 플레이어의 닉네임
     * @return 스킨 Base64 인코드 문자열
     */
    fun getSkinData(playerName: String): String

    /***
     * 플레이어가 특정 이름으로 사용 가능한지 확인합니다.
     *
     * @param requestName 요청하는 닉네임
     * @return 이름 사용 가능 여부 (AVAILABLE, DUPLICATE, NOT_ALLOWED)
     */
    fun isNameAvailable(requestName: String): NameAvailability
}