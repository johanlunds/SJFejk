package com.example.johan_lunds.sjfejk

import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.Px
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowManager


val Resources.navBarHeight: Int @Px get() {
    val id = getIdentifier("navigation_bar_height", "dimen", "android")
    return when {
        id > 0 -> getDimensionPixelSize(id)
        else -> 0
    }
}

val Resources.showsSoftwareNavBar: Boolean get() {
    val id = getIdentifier("config_showNavigationBar", "bool", "android")
    return id > 0 && getBoolean(id)
}

val Resources.statusBarHeight: Int @Px get() {
    val id = getIdentifier("status_bar_height", "dimen", "android")
    return when {
        id > 0 -> getDimensionPixelSize(id)
        else -> 0
    }
}

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // make system bar + nav bar transparent
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // special case: handle hardware vs software nav bar buttons
        // TODO: handle rotation and other special cases: https://michiganlabs.com/android/development/design/2018/03/20/a-more-immersive-android-navbar/
        // TODO: and multi window
        container.setPaddingRelative(
                container.paddingStart,
                container.paddingTop + resources.statusBarHeight,
                container.paddingEnd,
                container.paddingBottom + if (resources.showsSoftwareNavBar) resources.navBarHeight else 0
        )

        i_want_to_go_to_btn.setOnClickListener { startBuyTripSelectDestination() }
    }

    override fun onStart() {
        super.onStart()
        fading_text_view.startAnimation()
    }

    override fun onStop() {
        super.onStop()
        fading_text_view.stopAnimation()
    }

    private fun startBuyTripSelectDestination() {

    }
}
