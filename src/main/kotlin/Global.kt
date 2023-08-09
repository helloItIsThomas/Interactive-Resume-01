
import Global.height
import Global.width
import classes.ParagraphStyle
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.extra.shapes.RoundedRectangle
import org.openrndr.math.Vector2
import java.io.File
import kotlin.properties.Delegates

object Global {
    lateinit var drawer: Drawer
    var width by Delegates.notNull<Int>()
    var height by Delegates.notNull<Int>()
    var frameCount by Delegates.notNull<Int>()
    var clock by Delegates.notNull<Double>()

    lateinit var monoReg: FontImageMap
    lateinit var monoBold2: FontImageMap
    lateinit var monoBold: FontImageMap
    lateinit var maruMini: FontImageMap
    lateinit var vecList: List<Vector2>
    lateinit var h3: ParagraphStyle
    lateinit var h32: ParagraphStyle
    lateinit var h31: ParagraphStyle
    lateinit var h1: ParagraphStyle
}

object Selector {
    var currentSection = 0
    fun check(_randomNum: Double) {
        if(_randomNum.toInt() != currentSection){
            println("State Changed")
            currentSection = _randomNum.toInt()
        }
    }
}

object Mouse {
    var currentSection = 0
    var pos = Vector2(0.0, 0.0)
    val rad = 3.0
}

object LinePath {
    val margin = 10.0
    var outline: RoundedRectangle = RoundedRectangle(
        margin,
        margin,
        width - (margin*2.0),
        height - (margin*2.0),
        10.0
    )
}

object VecList {
    val vecList = listOf(
        Vector2(306.888437030501, 200.0),
        Vector2(304.95158061983074, 206.60299377244124),
        Vector2(297.63542808425007, 212.334222944203),
        Vector2(293.4924650218396, 217.8346415334784),
        Vector2(297.07672621814413, 224.92506690018496),
        Vector2(293.2973914684153, 230.31416009524006),
        Vector2(298.25503368508106, 238.90191984128717),
        Vector2(286.92334592021035, 240.90302179350766),
        Vector2(287.2205030961931, 247.94987734372606),
        Vector2(285.8408282381454, 254.4762461170997),
        Vector2(287.5051046324231, 263.5761799330143),
        Vector2(277.12354996706455, 263.80214926155895),
        Vector2(269.7161953996763, 265.46786514808196),
        Vector2(271.95691114567325, 276.6263275340035),
        Vector2(265.25142373733576, 278.87542186702746),
        Vector2(255.84555136749432, 276.8648072409634),
        Vector2(257.9737399597018, 291.35199667587915),
        Vector2(252.82704094785322, 296.0920307579854),
        Vector2(245.21961065598532, 296.09656419202094),
        Vector2(239.77339848067942, 300.4561374716976),
        Vector2(229.7283468874204, 291.4944438028489),
        Vector2(226.01212534731974, 301.3105393380134),
        Vector2(220.23282831270373, 306.0642003803372),
        Vector2(212.9945093786472, 302.8621334046268),
        Vector2(206.24406848554335, 299.2466265516347),
        Vector2(200.0, 292.01402416136733),
        Vector2(193.80361574009902, 298.4887074888172),
        Vector2(187.18872018473218, 301.41172206220506),
        Vector2(179.7140574595445, 306.34263491298117),
        Vector2(172.81020558448827, 305.8972575268261),
        Vector2(169.24038795749905, 294.66835159310295),
        Vector2(160.49795365648808, 299.77078020689726),
        Vector2(159.4616191316067, 286.14844450998214),
        Vector2(148.88566706249273, 292.97662645260186),
        Vector2(145.89543266426168, 285.2551561938194),
        Vector2(146.93425720820696, 273.0387289749974),
        Vector2(133.45670026964916, 280.4370316850619),
        Vector2(132.930490434301, 271.42177347109845),
        Vector2(122.36710131552913, 272.9021443725147),
        Vector2(120.35739035513542, 265.8860966632334),
        Vector2(127.16997930131728, 252.9141073531037),
        Vector2(115.6756551834911, 253.51385647332347),
        Vector2(105.92667664027387, 251.7172471642442),
        Vector2(114.15162208209453, 240.39718024817694),
        Vector2(110.27276887824145, 235.52552395297081),
        Vector2(97.84756678254804, 233.19133757046674),
        Vector2(109.12622814892244, 223.3324189133699),
        Vector2(100.44497613115237, 218.9911364850956),
        Vector2(105.97498946675769, 211.8781211390533),
        Vector2(90.8649738290607, 206.86619385726303),
        Vector2(93.9364106144026, 200.0),
        Vector2(101.23588232515041, 193.78628839988653),
        Vector2(109.11344729679205, 188.51835828789427),
        Vector2(105.30643628028098, 181.93623663602966),
        Vector2(102.98786043445749, 175.09151613366538),
        Vector2(96.66135979040561, 166.423240413977),
        Vector2(114.29212785509009, 166.06585284337615),
        Vector2(108.58953493003476, 156.98550020907425),
        Vector2(108.74910912950098, 149.83439822720837),
        Vector2(114.76609372488142, 145.90891827274052),
        Vector2(114.01011983109574, 137.5246950792063),
        Vector2(122.32789466967287, 135.7440462756012),
        Vector2(120.34066229378551, 125.19490272184044),
        Vector2(130.13258094724316, 125.59875554616633),
        Vector2(135.1406166527956, 121.59847355761487),
        Vector2(141.86816755214267, 119.98839681490969),
        Vector2(145.38545889328714, 113.94125372792719),
        Vector2(152.93361850607982, 114.38656988860467),
        Vector2(156.77785813914667, 108.14827307014039),
        Vector2(164.7312418943154, 110.92128538018295),
        Vector2(171.0179677240438, 110.80247639040785),
        Vector2(176.6891552489187, 109.21025396334672),
        Vector2(180.83923680755169, 99.5557617909498),
        Vector2(187.07398408776396, 97.68004821024114),
        Vector2(193.75042067116004, 100.66578110245955),
        Vector2(199.99999999999997, 108.20351277608813),
        Vector2(206.6025536347925, 95.05541516776375),
        Vector2(213.47776033415923, 93.31254139029257),
        Vector2(220.3248052853455, 93.45363944383931),
        Vector2(226.57231660150916, 96.50765977733822),
        Vector2(233.3625446615528, 97.3206455368648),
        Vector2(239.92739594661862, 99.15490932843421),
        Vector2(242.92366130214674, 108.78257654497621),
        Vector2(247.12799686639482, 114.27449194214468),
        Vector2(255.78260642202724, 112.10067730701708),
        Vector2(256.1409461370398, 122.72861673883239),
        Vector2(267.7151912688154, 118.14639478773232),
        Vector2(273.23950271348923, 122.00785117095973),
        Vector2(278.65628301940103, 126.13683376951134),
        Vector2(278.4351843001329, 135.1127724513193),
        Vector2(288.1790479558483, 135.93417158105197),
        Vector2(285.77856701211545, 145.5632660584276),
        Vector2(286.7642304063916, 152.3009606901607),
        Vector2(293.3825923188059, 156.05748756776435),
        Vector2(302.2058259881984, 159.53385082896625),
        Vector2(303.03634487433465, 166.52146212248218),
        Vector2(302.5405108573666, 173.6720716420151),
        Vector2(290.0241312877818, 182.82697851099192),
        Vector2(301.4493456628635, 187.18396721864605),
        Vector2(299.5320918676282, 193.7379715590885)
    )
}