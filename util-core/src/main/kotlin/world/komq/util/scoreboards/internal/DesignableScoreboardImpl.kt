/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.internal

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Objective
import world.komq.util.scoreboards.DesignableScoreboard
import world.komq.util.scoreboards.entry.DesignableEntry
import world.komq.util.scoreboards.internal.entries.DesignableBlankEntry

/***
 * @author Dytro
 */

@Suppress("UNUSED")
class DesignableScoreboardImpl(override val objective: Objective) : DesignableScoreboard {
    private val entries = ArrayList<DesignableEntry>()
    private var orderNotUpdated = false

    override fun reloadAllEntries() {
        DesignableBlankEntry.repeated = 0

        objective.scoreboard?.let {
            it.entries.forEach { entry ->
                it.resetScores(entry)
            }
        }

        if (orderNotUpdated) {
            orderNotUpdated = false
            entries.sortBy { it.order() }
        }

        entries.sortedBy { it.order() }.forEachIndexed { index, entry ->
            entry.load(index)
        }
    }

    override fun addEntry(entry: DesignableEntry) {
        orderNotUpdated = true
        entries.add(entry.apply {
            parent = this@DesignableScoreboardImpl
        })
    }

    override fun removeEntry(entry: DesignableEntry) {
        entries.remove(entry)
    }

    override fun retrieveEntryOrder(entry: DesignableEntry) = entries.indexOf(entry)

    override fun getEntry(key: String) = entries.find { it.key == key }

    override fun sendScoreboard(player: Player) {
        objective.scoreboard?.let {
            player.scoreboard = it
        }
    }

    override fun markAsSortingUpdateNeeded() {
        orderNotUpdated = true
    }

    override fun entries() = entries

    override fun editEntry(from: String, to: DesignableEntry) {
        if (entries.removeIf { it.key == from }) addEntry(to)
    }
}