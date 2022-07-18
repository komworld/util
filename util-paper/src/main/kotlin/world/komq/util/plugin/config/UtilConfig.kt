/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.plugin.config

import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

@Suppress("UNUSED")
object UtilConfig {
    @Config
    var administrator = arrayListOf(
        "5082c832-7f7c-4b04-b0c7-2825062b7638"
    )

    fun load(configFile: File) {
        ConfigSupport.compute(this, configFile)
    }
}