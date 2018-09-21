package com.zzcandor.utilandroid.ui.activity

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.zzcandor.utilandroid.R
import com.zzcandor.utilandroid.adapter.GridAdapter
import com.zzcandor.utilandroid.api.RequestClient
import com.zzcandor.utilandroid.util.Shares
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mContext: Context? = null
    private var mAdapter: BaseAdapter? = null
    private var mData: ArrayList<Icon>? = null

    private var mHeader:Image? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        request()

        fab.setOnClickListener { view ->
            request()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        mContext = this@MainActivity
        val headerView = nav_view.getHeaderView(0)
//        imageView.setImageResource()
       val imageView=headerView.findViewById(R.id.imageView) as ImageView
        imageView.setOnClickListener {
//            Intent(this@MainActivity, QrActivity::class.java).run {
//                startActivity(this)
//            }
            Toast.makeText(this,"头像",Toast.LENGTH_SHORT).show();

        }
        fab.animate().alpha(0.5F).setDuration(500).start()


        mData = ArrayList()
        mData!!.add(Icon(R.drawable.ic_menu_camera, "Qr"))
        mData!!.add(Icon(R.drawable.ic_menu_send, "URL"))
        mData!!.add(Icon(R.drawable.ic_menu_manage, "图标3"))
        mData!!.add(Icon(R.drawable.ic_menu_slideshow, "video"))
        mData!!.add(Icon(R.drawable.ic_menu_share, "share"))
        mData!!.add(Icon(R.mipmap.iv_icon_6, "图标6"))
        mData!!.add(Icon(R.mipmap.iv_icon_7, "图标7"))

        mAdapter = object : GridAdapter<Icon>(mData, R.layout.item_grid_icon) {
            override fun bindView(holder: ViewHolder, obj: Icon) {
                holder.setImageResource(R.id.img_icon, obj.getiId())
                holder.setText(R.id.txt_icon, obj.getiName())
            }
        }


        grid_photo!!.adapter = mAdapter

        grid_photo!!.onItemClickListener = object : AdapterView.OnItemClickListener {
            override  fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(mContext, "你点击了~$position~项", Toast.LENGTH_SHORT).show()

                when (position){

                    0 -> {
                        // Handle the camera action
                        Toast.makeText(mContext,"camera",Toast.LENGTH_LONG).show();

                        Intent(this@MainActivity, QrActivity::class.java).run {
                            startActivity(this)
                        }
                    }

                    1 -> {
                        // Handle the camera action
                        Toast.makeText(mContext,"camera",Toast.LENGTH_LONG).show();

                        Intent(this@MainActivity, WebActivity::class.java).run {
                            startActivity(this)
                        }
                    }


                    2 -> {
                        // Handle the camera action
                        Toast.makeText(mContext,"camera",Toast.LENGTH_LONG).show();

                        Intent(this@MainActivity, AboutActivity::class.java).run {
                            startActivity(this)
                        }
                    }


                    3 -> {

                        Intent(this@MainActivity, VideoActivity::class.java).run {
                            startActivity(this)
                        }
                    }


                    4 -> {
                        Shares.share(this@MainActivity,"百度")

                    }

                    5 -> {
                        Intent(this@MainActivity, MarqueeActivity::class.java).run {
                            startActivity(this)
                        }

                    }

                }


            }
        }



    }
    private fun request(){
        RequestClient
                .getApiService()
                .getProjectTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Any>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Any) {
//                        Log.d("",t.toString())
                        System.out.println("OKKK")
                        System.out.println(t)

                    }

                    override fun onError(e: Throwable) {


                    }


                })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
                Toast.makeText(this,"camera",Toast.LENGTH_LONG).show();
            }
            R.id.nav_gallery -> {
                Toast.makeText(this,"gallery",Toast.LENGTH_LONG).show();

            }
            R.id.nav_slideshow -> {
                Toast.makeText(this,"slideshow",Toast.LENGTH_LONG).show();

            }
            R.id.nav_manage -> {
                Toast.makeText(this,"manage",Toast.LENGTH_LONG).show();

            }
            R.id.nav_share -> {
                Toast.makeText(this,"share",Toast.LENGTH_LONG).show();

            }
            R.id.nav_send -> {

               Toast.makeText(this,"Send",Toast.LENGTH_LONG).show();

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
