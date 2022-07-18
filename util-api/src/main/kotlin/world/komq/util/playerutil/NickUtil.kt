/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.playerutil

import org.bukkit.entity.Player
import world.komq.util.LibraryLoader

/***
 * @author BaeHyeonWoo × Dytro
 */

interface NickUtil {
    companion object: NickUtil by LibraryLoader.loadImplement(NickUtil::class.java)

    fun Player.nick(name: String?, defaultSkins: Boolean, changeSkinTo: String? = null)

    fun Player.unNick()

    fun Player.isNicked(): Boolean
}