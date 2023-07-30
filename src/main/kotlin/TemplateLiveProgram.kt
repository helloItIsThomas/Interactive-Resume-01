

import demos.classes.Animation
import kotlinx.coroutines.delay
import org.openrndr.*
import org.openrndr.animatable.Animatable
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.draw.launch
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.*
import org.openrndr.shape.Rectangle

import java.io.File


fun main() = application {
    configure {
        val aspectRatio = 1.294
        width = 350
        height = (width * aspectRatio).toInt()
        hideWindowDecorations = true
        windowAlwaysOnTop = true
        position = IntVector2(1380,110)
        windowTransparent = false
    }

    oliveProgram {

        val animation = Animation()
        val monoReg = loadFont(("data/fonts/mono-reg.otf"), width*0.013)
        val monoBold2 = loadFont(("data/fonts/mono-bold.otf"), width*0.013)
        val monoBold = loadFont(("data/fonts/mono-bold.otf"), width*0.0193)
        val maruMini = loadFont(("data/fonts/maru-mini.otf"), width*0.033)
        val resumeImg = loadImage(File("data/images/resume.jpg"))
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
        val hypeLowThresh = 0.25
        val hypeHighThresh = 0.75

        data class ParagraphStyle(
            val font: FontImageMap,
            val textColor: ColorRGBa,
            val leading: Double
        )

        val h3 = ParagraphStyle(monoReg, ColorRGBa.BLACK, 1.2)
        val h32 = ParagraphStyle(monoBold, ColorRGBa.BLACK, 1.2)
        val h31 = ParagraphStyle(monoBold2, ColorRGBa.BLACK, 1.2)
        val h1 = ParagraphStyle(maruMini, ColorRGBa.BLACK, 0.1)


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

        data class CustomText(
            val txt: String,
            val style: ParagraphStyle
        )

        val headBlock = mutableListOf(
            CustomText(
                "\nhello,\nit's thomas",
                h1
            ),
        )
        val infoBlock = mutableListOf(
            CustomText(
                "thomas mcelmeel",
                h3
            ),
        )
        val skillsBlock = mutableListOf(
            CustomText(
                "\nskills",
                h1
            ),
            CustomText(
                "Arduino, " +
                        "C++, CSS, Digital Photography, GitHub, HTML, JavaScript, openFrameworks, " +
                        " OPENRNDR, p5.js, Processing, Python, TouchDesigner.",
                h3
            ),
        )
        val eduBlock = mutableListOf(
            CustomText(
                "\neducation",
                h1
            ),
            CustomText(
                "College for Creative Studies",
                h32
            ),
            CustomText(
                "BFA in Communication Design",
                h3
            ),
            CustomText(
                "\nOakland Community College",
                h32
            ),
            CustomText(
                "Intro to C++",
                h3
            ),
            CustomText(
                "Intro to Java",
                h3
            ),
            CustomText(
                "Intro to HTML / CSS",
                h3
            ),
        )
        val invBlock = mutableListOf(
            CustomText(
                "\ninvolvement",
                h1
            ),
            CustomText(
                "CCS Student Activities",
                h31
            ),
            CustomText(
                "Collaborated to design, produce, and perform interactive visuals for the Embrace Your Voice Block Party.",
                h3
            ),
            CustomText(
                "\nVice-President and Co-Creator of CCS’s Creative Coding Community",
                h31
            ),
            CustomText(
                "\nDetroit Historical Society\n" +
                        "Sponsored Studio",
                h31
            ),
            CustomText(
                "Designed and installed an interactive\n" +
                        "poetry portal that was selected for an " +
                        "upcoming exhibition at the Detroit\n" +
                        "Historical Society Museum.",
                h3
            ),
            CustomText(
                "\nHonda Sponsored Studio",
                h31
            ),
            CustomText(
                "Designed and prototyped an in-vehicle wrong way alert system for hearing- impaired drivers.",
                h3
            ),
        )
        val expBlock = mutableListOf(
            CustomText(
                "\nexperience",
                h1
            ),
            CustomText(
                "RNDR",
                h32
            ),
            CustomText(
                "Creative Coding Intern",
                h31
            ),
            CustomText(
                "Wrote small, bespoke programs with the\n" +
                        "OPENRNDR Kotlin framework for various\n" +
                        "studio projects, and assisted with the\n" +
                        "installation of RNDR’s machine-learning\n" +
                        "driven Oracle system for the Highlight\n" +
                        "Delft 2023 Festival.",
                h3
            ),
            CustomText(
                "\n2x4",
                h32
            ),
            CustomText(
                "Design Intern",
                h31
            ),
            CustomText(
                "Worked with a small team of architects\n" +
                        "and designers to program and produce\n" +
                        "a set of 67 large-scale wall graphics\n" +
                        "for YouTube.",
                h3
            ),
            CustomText(
                "\nMedia Monks",
                h32
            ),
            CustomText(
                "Design Intern",
                h31
            ),
            CustomText(
                "Worked closely with teams of creatives\n" +
                        "on identity designs, web content, and\n" +
                        "installations for brands including TikTok\n" +
                        "and Pearson.",
                h3
            ),
        )

        val recBlock = mutableListOf(
            CustomText(
                "\nrecognition",
                h1
            ),
            CustomText(
                "Imre J. Molnar Award for Visual Design Excellence",
                h31
            ),
            CustomText(
                "Design Nation Conference",
                h31
            ),
            CustomText(
                "CCS Merit + MI Competitive Scholarship",
                h31
            )
        )

        val Selector = object {
            var currentSection = 100
        }

        class Section( val id: Int, var hype: Double, _x: Double, _y: Double, _w: Double, _h: Double, val txt: MutableList<CustomText> ) {
            var thisRect = Rectangle(_x, _y, _w, _h)
            var isSelected = false
            fun check(){
                if(Selector.currentSection == id){
                    isSelected = true
                }
            }
            fun render(drawer: Drawer){
                drawer.fill = ColorRGBa.FOREST_GREEN
//                if(isSelected) drawer.rectangle(thisRect)
            }
        }


        val MrLine = object {
            var startPos = Vector2(width * 0.12, height * 0.15)
            var drawPos = startPos
            var endPos = Vector2((width*0.32) + (width * 0.563), height * 0.15)

            fun seek(section: Section) {
                // so I want to move towards the section.
                // but not all at once.
                // so I get the direction I need to go,
                // normalize it,
                // then multiply it by the magnitude I want to go.
                val direction = section.thisRect.corner - startPos
                val normDir = direction.normalized
            }

            fun superSeek(section: Section) {}

            fun check(section: Section){
                if(section.hype > hypeLowThresh && section.hype < hypeHighThresh){
                    Selector.currentSection = section.id
                    this.seek(section)
                } else if(section.hype > hypeHighThresh){
                    this.superSeek(section)
                }
            }
        }

        val sections = mutableListOf(
            Section(0, 0.0, width * 0.12, height * 0.05, width * 0.17, height * 0.1, headBlock),
            Section(1, 0.0, width * 0.32, height * 0.05, width * 0.59, height * 0.1, infoBlock),
            Section(2, 0.0, width * 0.12, height * 0.22, width * 0.35, height * 0.12, skillsBlock),
            Section(3, 0.0, width * 0.12, height * 0.353, width * 0.35, height * 0.2, eduBlock),
            Section(4, 0.5, width * 0.12, height * 0.575, width * 0.35, height * 0.39, invBlock),
            Section(5, 0.0, width * 0.563, height * 0.22, width * 0.35, height * 0.46, expBlock),
            Section(6, 0.0, width * 0.563, height * 0.693, width * 0.35, height * 0.275, recBlock)
        )

        fun writerCall(section: Section) {
            drawer.pushTransforms()
//            drawer.translate(-width*0.5, -height*0.5)
            writer {
                this.box = section.thisRect
                section.txt.forEach{ e ->
                    drawer.fontMap = e.style.font
                    leading = e.style.leading
                    drawer.pushStyle()
                    drawer.fill = e.style.textColor
                    drawer.stroke = null
                    drawer.pushTransforms()
                    var state1 = Vector2(0.0, 0.0)
                    e.txt.forEachIndexed { i, c ->
                        drawer.pushTransforms()
                        val index = (i + frameCount) % vecList.size
                        var state2X = vecList[index].x
                        var state2Y = vecList[index].y
                        var state2 = Vector2(state2X, state2Y)
                        var moveVect = mix(state1, state2, 1.0)
                        drawer.translate(-width*0.5, -height*0.5)
                        drawer.translate(moveVect)
                        drawer.translate(i*0.0025, i*0.0025)
                        text(c.toString())
                        drawer.popTransforms()
                    }
                    drawer.popTransforms()
                    drawer.popStyle()
                    gaplessNewLine()
                    gaplessNewLine()
                }
            }
            drawer.popTransforms()
        }

        extend {
            drawer.clear(ColorRGBa.WHITE)
//            drawer.fill = alphaColor
//            drawer.rectangle(drawer.bounds)
            animation(((frameCount * 0.01) ) % 2.0)


//            writerCall(sections[2])
//            writerCall(sections[5])
            sections.forEach { e ->
//                writerCall(e)
                e.check()
                e.render(drawer)
                MrLine.check(e)
            }

            drawer.fill = null
            drawer.stroke = ColorRGBa.BLACK
            drawer.strokeWeight = 0.75

            drawer.lineSegment(
                MrLine.drawPos,
                MrLine.endPos
            )

            MrLine.drawPos = mix(MrLine.startPos, sections[4].thisRect.corner, animation.pathSlider)
        }
    }
}


