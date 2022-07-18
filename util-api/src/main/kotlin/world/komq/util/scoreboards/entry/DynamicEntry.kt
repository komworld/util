/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.entry

import net.kyori.adventure.text.Component

/***
 * @author Dytro
 */

interface DynamicEntry : DesignableEntry {
    fun changeDisplay(to: Component)
}