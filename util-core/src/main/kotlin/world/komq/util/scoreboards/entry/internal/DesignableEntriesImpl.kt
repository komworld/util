/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.entry.internal

import net.kyori.adventure.text.Component
import world.komq.util.scoreboards.entry.DesignableEntries
import world.komq.util.scoreboards.internal.entries.DesignableBlankEntry
import world.komq.util.scoreboards.internal.entries.DesignableDynamicStringEntry
import world.komq.util.scoreboards.internal.entries.DesignableFixedStringEntry
import world.komq.util.scoreboards.internal.entries.DesignableScoreEntry

/***
 * @author Dytro
 */

class DesignableEntriesImpl : DesignableEntries {

    override fun blank(order: Int) = DesignableBlankEntry(order)

    override fun dynamic(order: Int, key: String, display: Component) = DesignableDynamicStringEntry(key, display, order)

    override fun fixed(order: Int, key: String, display: Component) = DesignableFixedStringEntry(key, display, order)

    override fun score(key: String, value: Int, display: Component) = DesignableScoreEntry(key, display, value)
}