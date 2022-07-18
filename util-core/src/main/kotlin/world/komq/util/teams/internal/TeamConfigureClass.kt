/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.teams.internal

import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import world.komq.util.teams.internal.TeamContentManager.sc
import world.komq.util.teams.internal.TeamContentManager.team
import world.komq.util.teams.internal.TeamContentManager.teamColor

/***
 * @author BaeHyeonWoo × Dytro
 */

class TeamConfigureClass(name: String, private val teamColor: NamedTextColor) {
    
    var full = false

    private val configuredTeam = sc.getTeam(name)?: sc.registerNewTeam(name).apply {
        color(teamColor)
    }

    fun addPlayer(player: Player) {
        configuredTeam.addEntry(player.name)
        player.team = configuredTeam
        player.teamColor = ChatColor.valueOf(NamedTextColor.NAMES.key(teamColor).toString().uppercase())

        full = true
    }

    fun addPlayers(playerList: List<Player>) {
        playerList.forEach {
            configuredTeam.addEntry(it.name)
            it.team = configuredTeam
            it.teamColor = ChatColor.valueOf(NamedTextColor.NAMES.key(teamColor).toString().uppercase())

            full = true
        }
    }
}