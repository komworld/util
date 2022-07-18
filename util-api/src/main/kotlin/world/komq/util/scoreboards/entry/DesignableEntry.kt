/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.entry

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.ChatColor
import org.bukkit.scoreboard.Score
import world.komq.util.scoreboards.DesignableScoreboard

/***
 * @author Dytro
 */

interface DesignableEntry {
    var parent: DesignableScoreboard?
    val key: String

    fun render(): Component
    fun order(): Int

    fun load(order: Int) {
        retrieveScoreboardScore().score = order
    }

    fun unload() {
        parent?.objective?.scoreboard?.resetScores(retrieveScoreboardScore().entry)
    }

    fun retrieveScoreboardScore(): Score {
        val legacy = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(render()))
        return requireNotNull(parent).objective.getScore(legacy)
    }

}