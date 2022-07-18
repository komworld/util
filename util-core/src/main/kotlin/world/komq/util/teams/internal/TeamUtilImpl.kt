/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.teams.internal

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import world.komq.util.objects.UtilObject.server
import world.komq.util.teams.internal.TeamContentManager.sc
import world.komq.util.teams.TeamUtil
import world.komq.util.teams.internal.TeamContentManager.setupTeams
import world.komq.util.teams.internal.TeamContentManager.teams

/***
 * @author BaeHyeonWoo × Dytro
 */

@Suppress("UNUSED")
class TeamUtilImpl: TeamUtil {
    private fun configureTeams(players: List<Player>, scoreboard: Scoreboard, minimumPlayers: Int, maximumPlayers: Int, divideTeam: (List<TeamConfigureClass>, List<Player>) -> Unit): Boolean {
        sc = scoreboard
        setupTeams()

        val filteredPlayers = players.asSequence().filter { !it.hasMetadata("spectator") && it.gameMode != GameMode.SPECTATOR }.shuffled().toList()

        if (filteredPlayers.size !in minimumPlayers..maximumPlayers) {
            server.broadcast(text("최소/최대 플레이 가능 플레이어 수가 적거나 많습니다.", NamedTextColor.RED))
            server.broadcast(text("플레이어들을 관전자로 바꿔주세요. 그렇지 않으면 게임이 실행 할 수 없습니다.", NamedTextColor.RED))
            server.broadcast(text("최소 플레이어 수: $minimumPlayers / 최대 플레이어 수: $maximumPlayers", NamedTextColor.RED))
            return false
        }

        divideTeam(teams, filteredPlayers)

        // Remove orphaned teams
        sc.teams.forEach { it.entries.ifEmpty { it.unregister() } }

        return true
    }

    override fun configureIndividualTeams(
        players: List<Player>,
        scoreboard: Scoreboard,
        minimumPlayers: Int,
        maximumPlayers: Int
    ) = configureTeams(players, scoreboard, minimumPlayers, maximumPlayers) { teams, filteredPlayers ->
        filteredPlayers.forEach {
            teams.firstOrNull { team -> !team.full }?.addPlayer(it)
        }
    }

    override fun configureTeamsByTeamCount(
        players: List<Player>,
        scoreboard: Scoreboard,
        minimumPlayers: Int,
        maximumPlayers: Int,
        teamCount: Int
    ) = configureTeams(players, scoreboard, minimumPlayers, maximumPlayers) { teams, filteredPlayers ->
        var count = 0
        val group = filteredPlayers.groupBy { count++ % teamCount }.values.toList()

        teams.take(count).forEachIndexed { index, team ->
            team.addPlayers(group[index])
        }
    }

    override fun configureTeamsByPlayerCount(
        players: List<Player>,
        scoreboard: Scoreboard,
        minimumPlayers: Int,
        maximumPlayers: Int,
        playerCount: Int
    ) = configureTeams(players, scoreboard, minimumPlayers, maximumPlayers) { teams, filteredPlayers ->
        val group = filteredPlayers.chunked(playerCount)

        teams.take(group.size).forEachIndexed { index, team ->
            team.addPlayers(group[index])
        }
    }
}