package com.jovvi.mobile.common_navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.jovvi.mobile.common_navigation.command.Command
import com.jovvi.mobile.common_navigation.command.Command.*
import java.util.*

class DefaultNavigator(
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : Navigator {

    constructor(activity: AppCompatActivity, containerId: Int) : this(
        activity,
        activity.supportFragmentManager,
        containerId
    )

    private lateinit var localStackCopy: LinkedList<String>

    override fun invoke(command: Command) {
        fragmentManager.executePendingTransactions()
        copyStackToLocal()

        applyCommand(command)
    }

    override fun invoke(commands: List<Command>) {
        fragmentManager.executePendingTransactions()
        copyStackToLocal()

        commands.forEach { applyCommand(it) }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList()
        val stackSize = fragmentManager.backStackEntryCount
        for (i in 0 until stackSize) {
            localStackCopy.add(fragmentManager.getBackStackEntryAt(i).name!!)
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> forward(command)
            is Replace -> replace(command)
            is BackTo -> backTo(command)
            is Back -> back()
        }
    }

    private fun forward(forward: Forward) {
        val screen: Screen = forward.screen
        val fragment: Fragment = screen.fragment

        fragmentManager.performBackStackTransaction(fragment, screen)
        localStackCopy.add(screen.screenKey)
    }

    private fun replace(replace: Replace) {
        val screen: Screen = replace.screen
        val fragment: Fragment = screen.fragment

        if (localStackCopy.isNotEmpty()) {
            fragmentManager.popBackStack()
            localStackCopy.removeLast()

            fragmentManager.performBackStackTransaction(fragment, screen)
            localStackCopy.add(screen.screenKey)
        } else {
            fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit()
        }
    }

    private fun backTo(backTo: BackTo) {
        val screen: Screen? = backTo.screen
        if (screen == null) {
            backToRoot()
            return
        }

        val key: String = screen.screenKey
        val index: Int = localStackCopy.indexOf(key)
        val size: Int = localStackCopy.size

        if (index != -1) {
            for (i in 1 until size - index) {
                localStackCopy.removeLast()
            }
            fragmentManager.popBackStack(key, 0)
        } else {
            backToRoot()
        }
    }

    private fun back() {
        if (localStackCopy.isNotEmpty()) {
            fragmentManager.popBackStack()
            localStackCopy.removeLast()
        } else {
            activityBack()
        }
    }

    private fun backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy.clear()
    }

    private fun activityBack() {
        activity.finish()
    }

    private fun FragmentManager.performBackStackTransaction(fragment: Fragment, screen: Screen) {
        beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(screen.screenKey)
            .commit()
    }
}
