/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.internal.entries

import net.kyori.adventure.text.Component
import world.komq.util.scoreboards.DesignableScoreboard
import world.komq.util.scoreboards.entry.DesignableEntry
import world.komq.util.scoreboards.entry.DynamicEntry

/***
 * @author Dytro
 */

class DesignableDynamicStringEntry(override val key: String, private var display: Component, private val order: Int) : DynamicEntry {
    override var parent: DesignableScoreboard? = null
    override fun render() = display
    override fun order() = if (order < 0) order - 100000 else order + 100000

    override fun changeDisplay(to: Component) {
        if (to == display) return
        parent?.let {
            unload()
            display = to
            load(it.retrieveEntryOrder(this))
        }
    }

    override fun equals(other: Any?) = key == (other as? DesignableEntry)?.key
    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + parent.hashCode()
        result = 31 * result + display.hashCode()
        result = 31 * result + order
        return result
    }
}