package com.example.modil.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.modil.MainActivity
import com.example.modil.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Animasi Fade In untuk foto, nama, dan kelas
        val imagePhoto: ImageView = findViewById(R.id.image_photo)
        val textName: TextView = findViewById(R.id.text_name)
        val textClass: TextView = findViewById(R.id.text_class)

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 2000  // Durasi animasi 2 detik

        // Menambahkan animasi pada elemen
        imagePhoto.startAnimation(fadeIn)
        textName.startAnimation(fadeIn)
        textClass.startAnimation(fadeIn)

        // Menunggu 5 detik, kemudian pindah ke MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Menutup SplashActivity setelah berpindah ke MainActivity
        }, 5000) // Delay selama 5 detik
    }
}
