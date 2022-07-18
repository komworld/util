/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards

import net.kyori.adventure.text.Component
import org.bukkit.Server
import org.bukkit.scoreboard.Scoreboard
import world.komq.util.LibraryLoader

/***
 * @author BaeHyeonWoo × Dytro
 */

interface ScoreboardUtil {
    companion object: ScoreboardUtil by LibraryLoader.loadImplement(ScoreboardUtil::class.java)

    /***
     * 점수를 자동으로 계산하여 스코어보드에 원하는 내용을 순위로 지정합니다.
     *
     * @param server 스코어보드가 있는 서버
     * @param name 스코어보드의 고유 이름
     * @param title 스코어보드의 디스플레이 타이틀
     */
    fun createDesignableScoreboard(server: Server, name: String, title: Component): DesignableScoreboard

    /***
     * 점수를 자동으로 계산하여 스코어보드에 원하는 내용을 순위로 지정합니다.
     *
     * @param scoreboard 변경사항을 만들 스코어보드
     * @param content 변경사항을 만들 내용
     * @param score 점수
     */
    fun calculateScores(scoreboard: Scoreboard, content: String, score: Int)
}