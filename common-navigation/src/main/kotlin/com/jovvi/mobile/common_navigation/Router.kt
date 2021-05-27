package com.jovvi.mobile.common_navigation

import com.jovvi.mobile.common_navigation.command.Command

// TODO add dispatcher between router and navigator in oreder
//      to remove var navigator: Navigator? = null and disable
//      navigation between onStop-onStart
class Router {

    private var navigator: Navigator? = null

    fun connectNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun disconnectNavigator() {
        this.navigator = null
    }

    fun forwardTo(screen: Screen) {
        navigator?.invoke(Command.Forward(screen))
    }

    fun newRootScreen(screen: Screen) {
        navigator?.invoke(listOf(Command.BackTo(null), Command.Replace(screen)))
    }

    fun backTo(screen: Screen?) {
        navigator?.invoke(Command.BackTo(screen))
    }

    fun replace(screen: Screen) {
        navigator?.invoke(Command.Replace(screen))
    }

    fun finish() {
        navigator?.invoke(listOf(Command.BackTo(null), Command.Back))
    }

    fun exit() {
        navigator?.invoke(Command.Back)
    }
}