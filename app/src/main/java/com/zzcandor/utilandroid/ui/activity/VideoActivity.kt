package com.zzcandor.utilandroid.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zzcandor.utilandroid.R
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd



class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val jzvdStd = findViewById<View>(R.id.videoplayer) as JzvdStd
        jzvdStd.setUp("blob:https://v.qq.com/7e3b5225-242b-486d-8758-141b1b2fc3f4", "饺子快长大", JzvdStd.SCREEN_WINDOW_NORMAL);
//        jzvdStd.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")


    }


    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }

    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }


}
