package com.farhad.justlikeolddays.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.farhad.justlikeolddays.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserListFragment())
                .commit()
        }
    }
}