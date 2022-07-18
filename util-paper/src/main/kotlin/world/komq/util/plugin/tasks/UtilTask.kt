/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.plugin.tasks

import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import world.komq.util.plugin.objects.UtilObject.display
import world.komq.util.scoreboards.entry.DynamicEntry

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class UtilTask : Runnable {
    override fun run() {
        // val max = display.entries().filter { it.key == "score_display" }.maxOf { PlainTextComponentSerializer.plainText().serialize(it.render()).length } - ("플레이어 점수".length)

        // (display["score_display"] as DynamicEntry).changeDisplay(text(" ".repeat(max / 2)).append(text("플레이어 점수", NamedTextColor.GOLD, TextDecoration.BOLD).append(text(" ".repeat(max / 2)))))
    }
}