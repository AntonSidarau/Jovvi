package com.jovvi.mobile.common_navigation

import com.jovvi.mobile.common_navigation.command.Command

interface Navigator {

    fun invoke(command: Command)

    fun invoke(commands: List<Command>)
}



