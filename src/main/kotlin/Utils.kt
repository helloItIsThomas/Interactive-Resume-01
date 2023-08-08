import Global.tracker
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.openrndr.KEY_SPACEBAR
import org.openrndr.Program
import org.openrndr.extra.noise.random


@OptIn(DelicateCoroutinesApi::class)
fun Program.setupKeyboardListeners() {
    var isTriggerActive = false

    keyboard.keyDown.listen { event ->
        if (event.key == KEY_SPACEBAR && !isTriggerActive) {
            val randomNumber = random(0.0, 5.0)
            Selector.check(randomNumber)
            tracker.triggerOn()
            isTriggerActive = true

            GlobalScope.launch {
                delay(1000)
                tracker.triggerOff()
                isTriggerActive = false
            }
        }
    }
}

