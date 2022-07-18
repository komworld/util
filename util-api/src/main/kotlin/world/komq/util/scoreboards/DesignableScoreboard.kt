/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Objective
import world.komq.util.scoreboards.entry.DesignableEntry

/***
 * @author BaeHyeoWoo × Dytro
 */

interface DesignableScoreboard {
    val objective: Objective

    /***
     * 스코어보드의 모든 항목들을 재로딩합니다.
     */
    fun reloadAllEntries()

    /***
     * 스코어보드에 새로운 항목을 추가합니다.
     *
     * @param entry 추가할 새로운 항목
     */
    fun addEntry(entry: DesignableEntry)

    /***
     * 스코어보드에 여러개의 새로운 항목을 추가합니다.
     *
     * @param entries 추가할 새로운 항목들
     */
    fun addEntries(vararg entries: DesignableEntry) = entries.forEach { addEntry(it) }

    /***
     * 스코어보드에서 항목을 불러옵니다.
     *
     * @param key 불러올 항목의 key 값
     * @return key 값에 해당하는 항목, 존재하지 않을 시 null 반환
     */
    fun getEntry(key: String): DesignableEntry?

    /***
     * 스코어보드에서 항목을 삭제합니다.
     *
     * @param entry 삭제할 항목
     */
    fun removeEntry(entry: DesignableEntry)

    /***
     * 스코어보드에서 항목의 순서를 불러옵니다.
     *
     * @param entry 순서를 불러오길 원하는 항목
     * @return 해당 항목의 순서
     */
    fun retrieveEntryOrder(entry: DesignableEntry): Int

    /***
     * 플레이어에게 스코어보드를 설정합니다.
     *
     * @param player 스코어보드를 설정시킬 플레이어
     */
    fun sendScoreboard(player: Player)

    /***
     * 스코어보드의 항목들을 불러옵니다.
     *
     * @return 항목들의 MutableList
     */
    fun entries(): MutableList<DesignableEntry>

    /***
     * 스코어보드에서 특정 항목을 수정합니다.
     *
     * @param from 수정하고 싶은 항목
     * @param to 변경 될 항목의 내용
     */
    fun editEntry(from: String, to: DesignableEntry)

    /***
     * 다음 로딩 때, 순서가 업데이트되도록 설정해준다.
     */
    fun markAsSortingUpdateNeeded()

    operator fun get(key: String) = getEntry(key)
}