package com.example.johan_lunds.sjfejk.buytrip.views

import android.content.Context
import android.support.v7.view.ContextThemeWrapper
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.TextSwitcher
import android.widget.TextView
import com.example.johan_lunds.sjfejk.R
import android.os.Handler


class FadingCyclingTextView @JvmOverloads constructor(
        ctx: Context, attrs: AttributeSet? = null
) : TextSwitcher(ctx, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()

        //
        // Configure
        //

        this.setFactory {
            TextView(ContextThemeWrapper(context, R.style.BigHeading), null, 0) }

        val inAnim = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_in)
        val outAnim = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_out)
        inAnim.duration = 350
        inAnim.startOffset = 500
        outAnim.duration = 350
        this.inAnimation = inAnim
        this.outAnimation = outAnim

        //
        // Initialize
        //

        updateText()
    }

    fun startAnimation() {
        textHandler.postDelayed(object : Runnable {
            override fun run() {
                switchText()
                textHandler.postDelayed(this, ANIM_DELAY)
            }
        }, ANIM_DELAY)
    }

    fun stopAnimation() {
        textHandler.removeCallbacksAndMessages(null);
    }

    companion object {
        private const val ANIM_DELAY: Long = 5000
    }

    private val textHandler = Handler()
    private var currentText = 0
    private val texts: List<String> = listOf(
            "Bring to the table",
            "Bleeding edge",
            "Content is king",
            "Competitive advantage",
            "Guesstimate",
            "Jump the shark",
            "Reinvent the wheel",
            "Zero-sum game"
    )

    private fun switchText() {
        currentText += 1
        currentText %= texts.size
        updateText()
    }

    private fun updateText() {
        setText(texts[currentText])
    }
}