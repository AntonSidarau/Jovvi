package com.jovvi.mobile.common_navigation.command

import com.jovvi.mobile.common_navigation.Screen

sealed class Command {

    object Back : Command()

    class BackTo(val screen: Screen?) : Command()

    class Forward(val screen: Screen) : Command()

    class Replace(val screen: Screen) : Command()
}
