import Global.h1
import Global.h3
import Global.h31
import Global.h32
import Global.height
import Global.width
import classes.CustomText
import classes.Section


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
val invBlock0 = mutableListOf(
    CustomText(
        "involvement",
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
    )
)

val invBlock1 = mutableListOf(
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

val expBlock0 = mutableListOf(
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
    )
)

val expBlock1 = mutableListOf(
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

val sections = mutableListOf(
    Section(
        0,
        0.0,
        width * 0.12,
        height * 0.05,
        width * 0.17,
        height * 0.1,
        headBlock,
    ),
    Section(
        1,
        0.0,
        width * 0.32,
        height * 0.05,
        width * 0.59,
        height * 0.1,
        infoBlock
    ),
    Section(
        2,
        0.0,
        width * 0.12,
        height * 0.22,
        width * 0.35,
        height * 0.12,
        skillsBlock
    ),
    Section(
        3,
        0.0,
        width * 0.12,
        height * 0.353,
        width * 0.35,
        height * 0.2,
        eduBlock
    ),
    Section(
        4,
        0.5,
        width * 0.12,
        height * 0.575,
        width * 0.35,
        height * 0.39,
        invBlock0
    ),
    Section(
        5,
        0.5,
        width * 0.12,
        height * 0.725,
        width * 0.35,
        height * 0.39,
        invBlock1
    ),
    Section(
        6,
        0.0,
        width * 0.563,
        height * 0.22,
        width * 0.35,
        height * 0.46,
        expBlock0
    ),
    Section(
        7,
        0.0,
        width * 0.563,
        height * 0.38,
        width * 0.35,
        height * 0.46,
        expBlock1
    ),
    Section(
        8,
        0.0,
        width * 0.563,
        height * 0.693,
        width * 0.35,
        height * 0.275,
        recBlock
    )
)