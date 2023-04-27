package com.zup.zupbank.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zup.zupbank.R
import com.zup.zupbank.ui.preLogin.MainActivity
import java.util.*

private val timer = Timer()

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide() // esconder action bar

        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    jump()
                }
            },
            3000,
        )
    }

    private fun jump() {
        timer.cancel()
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}
