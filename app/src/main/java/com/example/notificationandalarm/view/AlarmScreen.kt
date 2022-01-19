package com.example.notificationandalarm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationandalarm.R
import kotlinx.android.synthetic.main.activity_alarm_screen.*

class AlarmScreen : AppCompatActivity() {
    lateinit var temp:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_screen)
        temp=intent.getStringExtra("alarm Title") ?: ""
        tvAlarmTitle.text=temp
    }
}