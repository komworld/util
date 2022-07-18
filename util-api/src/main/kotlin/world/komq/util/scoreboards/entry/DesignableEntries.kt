/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.entry

import net.kyori.adventure.text.Component
import world.komq.util.LibraryLoader

/***
 * @author Dytro
 */

interface DesignableEntries {
    companion object: DesignableEntries by LibraryLoader.loadImplement(DesignableEntries::class.java)

    fun blank(order: Int): DesignableEntry

    fun fixed(order: Int, key: String, display: Component): DesignableEntry

    fun dynamic(order: Int, key: String, display: Component): DynamicEntry

    fun score(key: String, value: Int, display: Component): NumericEntry
}