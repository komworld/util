/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.plugin

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.plugin.java.JavaPlugin
import world.komq.util.plugin.commands.UtilKommand.register
import world.komq.util.plugin.config.UtilConfig.load
import world.komq.util.plugin.events.UtilEvent
import world.komq.util.plugin.objects.UtilObject.createTeams
import world.komq.util.plugin.objects.UtilObject.createTestScoreboard
import world.komq.util.plugin.objects.UtilObject.display
import world.komq.util.plugin.objects.UtilObject.event
import world.komq.util.plugin.objects.UtilObject.initScores
import world.komq.util.plugin.tasks.UtilConfigReloadTask
import world.komq.util.plugin.tasks.UtilTask
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class UtilPluginMain : JavaPlugin() {

    companion object {
        lateinit var instance: UtilPluginMain
            private set
    }

    private val configFile = File(dataFolder, "config.yml")

    override fun onEnable() {
        instance = this
        load(configFile)

        event = UtilEvent()

        server.pluginManager.registerEvents(event, this)
        server.scheduler.runTaskTimer(this, UtilTask(), 0L, 0L)
        server.scheduler.runTaskTimer(this, UtilConfigReloadTask(), 0L, 20L)

        createTeams()
        createTestScoreboard()

        server.sendMessage(text(display.entries().toString()))
        initScores()

        server.onlinePlayers.forEach {
            display.sendScoreboard(it)
        }

        kommand {
            register("util") {
                requires { isOp }
                register(this)
            }
        }
    }
}