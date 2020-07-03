package com.jovvi.mobile.common_navigation

import com.jovvi.mobile.common_navigation.command.Command
import com.jovvi.mobile.common_navigation.command.Command.*

interface Navigator {

    fun invoke(command: Command)

    fun invoke(commands: List<Command>)
}

fun Navigator.forwardTo(screen: Screen) {
    invoke(Forward(screen))
}

fun Navigator.newRootScreen(screen: Screen) {
    invoke(listOf(BackTo(null), Replace(screen)))
}

fun Navigator.backTo(screen: Screen?) {
    invoke(BackTo(screen))
}

fun Navigator.replace(screen: Screen) {
    invoke(Replace(screen))
}

fun Navigator.finish() {
    invoke(listOf(BackTo(null), Back))
}

fun Navigator.exit() {
    invoke(Back)
}


