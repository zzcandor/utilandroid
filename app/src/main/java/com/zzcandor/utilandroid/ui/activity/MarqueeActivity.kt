package com.zzcandor.utilandroid.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zzcandor.utilandroid.R
import kotlinx.android.synthetic.main.activity_marquee.*

class MarqueeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)

        resume.setOnClickListener {
            marquee2.resumeScroll()
        }

        pause.setOnClickListener {
            marquee2.pauseScroll()
        }

        restart.setOnClickListener {
            marquee2.startScroll()
        }

        stop.setOnClickListener {
            marquee2.stopScroll()
        }
    }
}
