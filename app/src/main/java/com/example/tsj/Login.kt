package com.example.tsj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    val ref = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (ref.currentUser!=null){
            val i = Intent(this,SplashScreen::class.java)
            startActivity(i)
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
}