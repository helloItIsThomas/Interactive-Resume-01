

This project involves the design of a text animation system using OPENRNDR, with a specific focus on manipulating individual words within various sections. The text is organized into CustomText objects, stored in different blocks (headBlock, infoBlock, skillsBlock, etc.) and placed within sections on the canvas.

Key Features:
Animated Motion: Each word should have two states and transition between them. The first state has a translation of (0.0, 0.0), and the second state translates by a Vector2 object found in the posTwo array, representing a wiggly or imperfect circle.

Transition Timing: The mix between the two states takes around 5 seconds, with the possibility of future adjustments. The transition back to the first state, triggered by a cursor hover over a section, will take about 0.075 seconds (referred to as the release value).

Random Section Selection: After the program starts, a section is randomly selected and begins transitioning from the first state to the second state. If the cursor hovers over the section, the transition reverts to the first state, and a new section is randomly selected.

Motion Exploration: The project's goal is to explore emergent motion from different rulesets. Various formations represented by Vector2's in the posTwo array can be manually swapped to observe different behaviors.

Content Structure: The content consists of different sections (e.g., headBlock, infoBlock), each containing a mutable list of CustomText objects. CustomText includes a string (txtStr) and a ParagraphStyle, detailing font, text color, and leading.

Intended Outcome:
The system should create engaging animations by translating words according to the described rulesets, allowing for creative exploration of motion and interaction with the content. It aims to provide a flexible framework where different movement patterns can be tested and applied to different sections of text.