/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.plugin.tasks

import world.komq.util.plugin.objects.UtilObject.plugin
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "You can't stop what you can't control..."
 */

class UtilConfigReloadTask: Runnable {
    private val configFile = File(plugin.dataFolder, "config.yml")

    private var configFileLastModified = configFile.lastModified()

    override fun run() {
        if (configFileLastModified != configFile.lastModified()) {
            plugin.reloadConfig()
            plugin.saveConfig()

            configFileLastModified = configFile.lastModified()
        }
    }
}