/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.plugin.objects

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.Listener
import world.komq.util.plugin.UtilPluginMain
import world.komq.util.scoreboards.DesignableScoreboard
import world.komq.util.scoreboards.ScoreboardUtil.Companion.createDesignableScoreboard
import world.komq.util.scoreboards.entry.DesignableEntries
import world.komq.util.teams.TeamUtil
import world.komq.util.teams.internal.TeamContentManager.team

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object UtilObject {
    val plugin = UtilPluginMain.instance

    val server = plugin.server
    lateinit var event: Listener

    lateinit var display: DesignableScoreboard

    fun createTeams() {
        TeamUtil.configureIndividualTeams(server.onlinePlayers.toList(), server.scoreboardManager.mainScoreboard, 1, 8)
    }

    fun createTestScoreboard() {
        display = createDesignableScoreboard(server, "util", text("POINTS", NamedTextColor.DARK_AQUA)).apply {
            addEntries(
                DesignableEntries.fixed(2, "score_display", text("플레이어 점수", NamedTextColor.GOLD, TextDecoration.BOLD)),
                DesignableEntries.blank(1),

                DesignableEntries.blank(-1),
                DesignableEntries.fixed(-2, "server_address", text("komq.world", NamedTextColor.YELLOW))
            )

            reloadAllEntries()
        }
    }

    fun initScores() {
        display.addEntries(*server.onlinePlayers.map {
            DesignableEntries.score("score_${it.name}", 0, it.displayName().color(it.team?.color()?: NamedTextColor.WHITE))
        }.toTypedArray())

        display.reloadAllEntries()
    }
}