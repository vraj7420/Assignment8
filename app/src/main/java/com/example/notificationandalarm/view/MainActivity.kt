package com.example.notificationandalarm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notificationandalarm.R
import com.example.notificationandalarm.adapter.SetNotificationAlarmAdapter
import com.example.notificationandalarm.model.AlarmList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val alarmList=ArrayList<AlarmList>()
    init {
        alarmList.add(AlarmList("Charge Mode Alarm","It will Set Alarm for Charging Mode on"))
        alarmList.add(AlarmList("Battery Low Alarm","It will Set Alarm for Battery Low"))
        alarmList.add(AlarmList("Charge Mode Alarm","It will Set Alarm for Charging Mode on"))
        alarmList.add(AlarmList("Gps Alarm","It will Set Alarm for Charging Mode on"))
        alarmList.add(AlarmList("Device Idle Alarm","It will Set Alarm for When Device is idle"))
        alarmList.add(AlarmList("Predefined Time","It will Set Alarm for  Predefined Time"))
        alarmList.add(AlarmList("After Certain Amount Time","It will Set Alarm for After Certain Time"))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapterAlarmList()
    }

   private fun setAdapterAlarmList(){
     val alarmAdapter=SetNotificationAlarmAdapter(alarmList,this@MainActivity)
       rvListAlarm.adapter=alarmAdapter
       rvListAlarm.addItemDecoration(
           DividerItemDecoration(
               this@MainActivity,
               DividerItemDecoration.VERTICAL
           )
       )
       rvListAlarm.layoutManager = LinearLayoutManager(this@MainActivity)
   }



}