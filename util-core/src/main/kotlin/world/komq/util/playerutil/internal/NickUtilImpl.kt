/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.util.playerutil.internal

import com.destroystokyo.paper.profile.ProfileProperty
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import world.komq.util.LibraryLoader
import world.komq.util.playerutil.NickUtil
import world.komq.util.request.MojangAPIUtil.Companion.getSkinData
import kotlin.random.Random.Default.nextInt

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

@Suppress("UNUSED")
class NickUtilImpl: NickUtil {
    private val nickNMS = LibraryLoader.loadNMS(NickNMS::class.java)

    override fun Player.nick(name: String?, defaultSkins: Boolean, changeSkinTo: String?) {
        var resultName = ""

        if (name == null) {
            resultName = ipsumName()
        }
        else {
            if (name.length !in 3..16) {
                sendMessage(text("설정하시는 플레이어의 이름이 너무 짧거나 깁니다! 3~16자리의 닉네임으로 설정해주세요.", NamedTextColor.RED))
            }
            else resultName = name
        }

        nickNMS.changeName(resultName)

        if (defaultSkins) {
            val defaultSkinData = ""
            playerProfile.setProperty(ProfileProperty("textures", defaultSkinData))
        }
        else {
            val skinData = getSkinData(resultName)
            playerProfile.setProperty(ProfileProperty("textures", skinData))
        }
    }

    override fun Player.unNick() {
        TODO("Not yet implemented")
    }

    override fun Player.isNicked(): Boolean {
        TODO("Not yet implemented")
    }

    private fun ipsumName(): String {
        val lipsum = "loremipsumdolorsitametconsecteturadipiscingelitnamconsecteturorcinecarcuportaegestasutmollisdolorauctorsuscipitduiidmattisurnamaecenasatfeugiaterosutmolliserosvivamusquisfringillanuncmorbiutanteutnequeultricesvulputateatsederatpellentesquenonsemmollispharetratortoracsuscipitnequenullafacilisisedsemperpulvinarfelisnonrhoncusmauristempornullaquisleovariusfaucibusinvelitorcicommodoquisaliquamidtinciduntfringillaleonullammagnaexfringillaatarcuidmalesuadacursusdolornameusemacextemporfacilisisvestibulumidodioidnequevestibulumblanditnecatmetusphasellusegetlaoreetenimcrasiddoloranequefringillagravidacrasscelerisqueeroseusollicitudinhendreriterosdolorconsequatliberoacbibendumnisiodioveleratutsempertellusatmetussemperinsemperdiamplaceratcurabitureratduiportasedullamcorpervelfeugiatnecrisusnullapretiumegeturnasitametvehiculasuspendisseconsecteturporttitorviverravestibulumideratvenenatisgravidadoloregetlobortisquamaeneanvenenatisloremnonplaceratmollisloremnuncauctorsemvelullamcorperodiosapiensednuncaeneanacleoquisvelitblanditauctordapibusnonurnadonecinsagittisestnonfermentumsemsedasollicitudinliberovestibulumvelnequeutelitimperdietvestibulumtinciduntetligulanuncegestasvenenatiserossitametaliquetvivamusnibhmassaplacerateteratnecpellentesquealiquetquamduispellentesqueliberoaliquetsagittisvelitidfeugiatleointegersitametfacilisistellussitamettinciduntliberointegerplaceratinturpisegetlobortisnullamsapienlectusaccumsannecelitutaccumsandapibusmassaduisnecmolestierisusfuscecursuslacussednisiviverravestibulumintegervelmattisnibhininterdumsitametmagnainultricesutrhoncusinterdummagnaquisconvalliserospharetranecvestibulumquisexcondimentumtristiquemassanonfinibusturpisinterdumetmalesuadafamesacanteipsumprimisinfaucibusmaecenasfermentumerosacullamcorperpretiumjustoerosmaximusjustoinpretiumnequeturpisalacusvestibulumeleifendipsumiderathendreritacondimentumlacusvariusduisacondimentumligulapellentesqueidmassavariusposuereurnaquismalesuadaerataeneanconsequaterosidestpharetraplaceratcommodonecrisusduissuscipitenimuttortorpretiumsollicitudinphasellusfeugiatnecquamimperdietpharetrapraesentplacerateusapiengravidaviverramorbiinultriciesjustosuspendissevehiculaquislectusnonvariusloremipsumdolorsitametconsecteturadipiscingelitmaurisegetipsumvitaeurnacondimentumefficiturinmolestieurnaatodioportaeusempersapiensemperaliquamatsapiennecvelitefficiturefficiturquisinnibhsedasollicitudinlacusatinterdumdiammaurisvenenatisurnanonlobortissollicitudinindictumlaoreetbibendumnuncetveliteusapiendapibusaccumsanvelvellacus"
        val from = nextInt(0, lipsum.length)
        val to = if (from == lipsum.length) nextInt(from - 15, from) else nextInt(from, from + 15)

        return (lipsum.substring(from + 1, to))
    }

    interface NickNMS {
        fun changeName(name: String)
    }
}