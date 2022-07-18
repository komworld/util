/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.scoreboards.entry

/***
 * @author Dytro
 */

interface NumericEntry : DesignableEntry {
    var value: Int

    operator fun plus(number: Int)
    operator fun plusAssign(number: Int)

    operator fun minus(number: Int)
    operator fun minusAssign(number: Int)
}