/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.teams

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import world.komq.util.LibraryLoader

/***
 * @author BaeHyeonWoo × Dytro
 */

interface TeamUtil {

    companion object: TeamUtil by LibraryLoader.loadImplement(TeamUtil::class.java)

    /***
     * 점수를 자동으로 계산하여 스코어보드에 원하는 내용을 순위로 지정합니다.
     *
     * @param players 팀에 넣을 플레이어들
     * @param scoreboard 변경사항을 만들 스코어보드
     * @param minimumPlayers 최소 플레이어 수
     * @param maximumPlayers 최대 플레이어 수
     * @return 플레이 가능 여부
     */
    fun configureIndividualTeams(players: List<Player>, scoreboard: Scoreboard, minimumPlayers: Int, maximumPlayers: Int): Boolean

    /***
     * 점수를 자동으로 계산하여 스코어보드에 원하는 내용을 순위로 지정합니다.
     *
     * @param players 팀에 넣을 플레이어들
     * @param scoreboard 변경사항을 만들 스코어보드
     * @param minimumPlayers 최소 플레이어 수
     * @param maximumPlayers 최대 플레이어 수
     * @param teamCount 만들어질 팀의 개수
     * @return 플레이 가능 여부
     */
    fun configureTeamsByTeamCount(players: List<Player>, scoreboard: Scoreboard, minimumPlayers: Int, maximumPlayers: Int, teamCount: Int): Boolean


    /***
     * 점수를 자동으로 계산하여 스코어보드에 원하는 내용을 순위로 지정합니다.
     *
     * @param players 팀에 넣을 플레이어들
     * @param scoreboard 변경사항을 만들 스코어보드
     * @param minimumPlayers 최소 플레이어 수
     * @param maximumPlayers 최대 플레이어 수
     * @param playerCount 각 팀에 있을 최대 플레이어 수
     * @return 플레이 가능 여부
     */
    fun configureTeamsByPlayerCount(players: List<Player>, scoreboard: Scoreboard, minimumPlayers: Int, maximumPlayers: Int, playerCount: Int): Boolean
}