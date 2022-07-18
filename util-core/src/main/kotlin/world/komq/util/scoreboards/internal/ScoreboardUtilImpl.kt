/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.internal

import net.kyori.adventure.text.Component
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard
import world.komq.util.scoreboards.DesignableScoreboard
import world.komq.util.scoreboards.ScoreboardUtil

/***
 * @author Dytro
 */

@Suppress("UNUSED")
class ScoreboardUtilImpl: ScoreboardUtil {

    override fun createDesignableScoreboard(server: Server, name: String, title: Component): DesignableScoreboard {
        val scoreboard = server.scoreboardManager.newScoreboard

        return DesignableScoreboardImpl(scoreboard.registerNewObjective(name, "dummy", title).apply {
            displaySlot = DisplaySlot.SIDEBAR
        })
    }

    override fun calculateScores(scoreboard: Scoreboard, content: String, score: Int) {

    }
}