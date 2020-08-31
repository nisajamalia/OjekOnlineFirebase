package com.nisa.ojekonlinefirebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.nisa.ojekonlinefirebase.MainActivity
import com.nisa.ojekonlinefirebase.R
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity() {

    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //todo 12
        auth = FirebaseAuth.getInstance()

        Handler().postDelayed(Runnable {
            //statement
            if (auth?.currentUser?.displayName != null){
                startActivity<MainActivity>()
            } else startActivity<LoginActivity>()
        },3000)
    }
}