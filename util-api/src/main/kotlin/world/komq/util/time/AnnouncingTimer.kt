/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.time


import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Server

/***
 * @author Dytro
 */

class AnnouncingTimer(
    private val server: Server,
    private val targetTime: Int,
    private val subject: String,
    private val action: String,
    private val onEnd: () -> Unit,
    private vararg val flags: Flags
) {

    enum class Flags {
        DISABLE_SOUND,
        DISABLE_ANNOUNCING_WHEN_MINUTES
    }

    fun update(currentTime: Int) {
        val difference = targetTime - currentTime

        when {
            difference < 0 -> return
            difference == 0 -> onEnd()
            difference % 60 == 0 && Flags.DISABLE_ANNOUNCING_WHEN_MINUTES !in flags -> broadcastTime(
                difference / 60,
                "분"
            )
            difference == 10 || difference <= 5 -> broadcastTime(difference, "초")
        }
    }

    private fun broadcastTime(difference: Int, units: String) {
        if (Flags.DISABLE_SOUND !in flags) server.playSound(Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.MASTER, 1000f, 1f))

        server.broadcast(
            text(subject, NamedTextColor.RED).append(text(" $difference$units ", NamedTextColor.GOLD)).append(text("뒤에 $action", NamedTextColor.RED))
        )
    }
}