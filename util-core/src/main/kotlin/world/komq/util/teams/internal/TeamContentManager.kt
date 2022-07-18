/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.teams.internal

import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team
import java.util.*
import kotlin.collections.HashMap

/***
 * @author BaeHyeonWoo × Dytro
 */

object TeamContentManager {

    lateinit var sc: Scoreboard

    lateinit var teams: List<TeamConfigureClass>

    private val teamMap = HashMap<UUID, Team?>()

    private val teamColorMap = HashMap<UUID, ChatColor?>()

    fun setupTeams() {
        teams = registerTeams(
            "Red" to NamedTextColor.RED,
            "Orange" to NamedTextColor.GOLD,
            "Yellow" to NamedTextColor.YELLOW,
            "Green" to NamedTextColor.GREEN,
            "DarkGreen" to NamedTextColor.DARK_GREEN,
            "Aqua" to NamedTextColor.AQUA,
            "Blue" to NamedTextColor.BLUE,
            "Purple" to NamedTextColor.DARK_PURPLE,
            "White" to NamedTextColor.WHITE,
            "Gray" to NamedTextColor.GRAY,
            "DarkAqua" to NamedTextColor.DARK_AQUA,
            "Pink" to NamedTextColor.LIGHT_PURPLE
        )
    }

    var Player.team: Team?
        get() {
            return teamMap[this.uniqueId]
        }
        set(value) {
            teamMap[this.uniqueId] = value
        }

    var Player.teamColor: ChatColor?
        get() {
            return teamColorMap[this.uniqueId]
        }
        set(value) {
            teamColorMap[this.uniqueId] = value
        }

    private fun registerTeams(vararg teamData: Pair<String, NamedTextColor>) = teamData.map { TeamConfigureClass(it.first, it.second) }
}