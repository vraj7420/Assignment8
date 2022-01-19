package com.example.notificationandalarm.adapter

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notificationandalarm.R
import com.example.notificationandalarm.model.AlarmList
import com.example.notificationandalarm.view.AlarmScreen

class SetNotificationAlarmAdapter(
    private var setAlarmList: ArrayList<AlarmList>,
    private var ctx:Context):
    RecyclerView.Adapter<SetNotificationAlarmAdapter.SetNotificationAlarmViewHolder>() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetNotificationAlarmViewHolder {
        val recyclerInflater = LayoutInflater.from(ctx)
        val recyclerView = recyclerInflater.inflate(R.layout.list_of_alaram_layout, null)
        return SetNotificationAlarmViewHolder(recyclerView)

    }


    override fun onBindViewHolder(holder:SetNotificationAlarmViewHolder, position: Int) {
        val alarmList=setAlarmList[position]
        holder.alarmTitle.text=alarmList.alarmTitle
        holder.alarmDescription.text=alarmList.alarmDescription
    }


    override fun getItemCount(): Int {
        return setAlarmList.size
    }


    inner class  SetNotificationAlarmViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
         var alarmTitle:TextView
         var alarmDescription:TextView

        @SuppressLint("UnspecifiedImmutableFlag")
        override fun onClick(v: View?) {
            Log.d("onclick","on Click")
            val  position=adapterPosition
            val  temp=setAlarmList[position]
            val intent = Intent(ctx,AlarmScreen::class.java)
            intent.putExtra("alarm Title",temp.alarmTitle)
            intent.putExtra("alarm Description",temp.alarmDescription)
            notificationManager= ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val pendingIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                notificationChannel = NotificationChannel(channelId,temp.alarmDescription, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)


                builder = Notification.Builder(ctx, channelId)
                    .setContentTitle(temp.alarmTitle)
                    .setContentText(temp.alarmDescription)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentText(temp.alarmDescription)
                    .setContentTitle(temp.alarmTitle)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
            }
            notificationManager.notify(1234, builder.build())
        }

      init {
            itemView.setOnClickListener(this)
          alarmTitle=itemView.findViewById(R.id.tvSetAlarm)
          alarmDescription=itemView.findViewById(R.id.tvSetAlarmDescription)
      }
    }
}