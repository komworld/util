/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.internal.entries

import net.kyori.adventure.text.Component.text
import world.komq.util.scoreboards.DesignableScoreboard
import world.komq.util.scoreboards.entry.DesignableEntry

class DesignableBlankEntry(private val order: Int) : DesignableEntry {
    companion object {
        var repeated = 0
    }

    override var parent: DesignableScoreboard? = null
    override val key = "blank"

    override fun render() = text(" ".repeat(repeated++))
    override fun order() = if (order < 0) order - 100000 else order + 100000
}