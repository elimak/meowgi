package com.elimak.krikey

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elimak.krikey.util.AnimationUtil
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AnimationUtil.fadeIn(logo, this)
        AnimationUtil.slideIn(name, this, 500)

        object:Thread() {
            override fun run() {
                super.run()
                try{
                    sleep(2000)
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}