/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.internal.entries

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import world.komq.util.scoreboards.DesignableScoreboard
import world.komq.util.scoreboards.entry.DynamicEntry
import world.komq.util.scoreboards.entry.NumericEntry

/***
 * @author Dytro
 */

class DesignableScoreEntry(override val key: String, private var display: Component, value: Int) : NumericEntry, DynamicEntry {
    override var parent: DesignableScoreboard? = null
    override var value = value
        set(value) {
            if (value == field) return
            field = value
            parent?.markAsSortingUpdateNeeded()
        }

    override fun changeDisplay(to: Component) {
        if (to == display) return
        parent?.let {
            unload()
            display = to
            load(it.retrieveEntryOrder(this))
        }
    }

    override fun render() = display.append(text(":", NamedTextColor.WHITE)).append(text(" $value", NamedTextColor.GREEN))
    override fun order() = value

    override fun plus(number: Int) {
    }

    override fun plusAssign(number: Int) {
        value += number
    }

    override fun minus(number: Int) {
    }

    override fun minusAssign(number: Int) {
        value -= number
    }


}