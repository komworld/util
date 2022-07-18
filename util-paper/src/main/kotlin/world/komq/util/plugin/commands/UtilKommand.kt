/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.plugin.commands

import io.github.monun.kommand.StringType
import io.github.monun.kommand.getValue
import io.github.monun.kommand.node.LiteralNode
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import world.komq.util.playerutil.NickUtil.Companion.isNicked
import world.komq.util.playerutil.NickUtil.Companion.nick
import world.komq.util.playerutil.NickUtil.Companion.unNick
import world.komq.util.plugin.objects.UtilObject.display
import world.komq.util.scoreboards.entry.NumericEntry

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object UtilKommand {
    fun register(builder: LiteralNode) {
        builder.apply {
            then("add", "target" to player(), "number" to int()) {
                executes {
                    val target: Player by it
                    val number: Int by it

                    (display["score_${target.name}"] as NumericEntry) += number
                    display.reloadAllEntries()
                }
            }

            then("nick") {
                executes {
                    TODO("NYI")
                }
                then("name" to string(StringType.QUOTABLE_PHRASE), "defaultSkins" to bool()) {
                    executes {
                        val name: String by it
                        val defaultSkins: Boolean by it

                        player.nick(name, defaultSkins, null)
                    }
                    then("changeSkinTo" to string(StringType.QUOTABLE_PHRASE)) {
                        executes {
                            val name: String by it
                            val defaultSkins: Boolean by it
                            val changeSkinTo: String by it

                            player.nick(name, defaultSkins, changeSkinTo)
                        }
                    }
                }
            }

            then("unnick") {
                executes {
                    if (player.isNicked()) player.unNick() else player.sendMessage(text("현재 닉네임 변경 상태가 아닙니다!", NamedTextColor.RED))
                }
            }
        }
    }
}