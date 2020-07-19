package com.madlab.todoandnotesapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager alarmNotificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            NotificationChannel alarmNotificationChannel = new NotificationChannel("ToDoAndNotesApp", "Show to-do alarms", NotificationManager.IMPORTANCE_HIGH);
            alarmNotificationChannel.setDescription("Show to-do alarms");
            alarmNotificationManager.createNotificationChannel(alarmNotificationChannel);
        }

        Intent newAppIntent=new Intent(context,MainActivity.class);
        newAppIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent newAppPendingIntent=PendingIntent.getActivity(context,1234,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder alarmNotificationBuilder=new NotificationCompat.Builder(context,"ToDoAndNotesApp");
        alarmNotificationBuilder.setContentTitle(intent.getStringExtra("todoTitle"));
        alarmNotificationBuilder.setContentText(intent.getStringExtra("todoDesc"));
        alarmNotificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(intent.getStringExtra("todoDesc")));
        alarmNotificationBuilder.setContentIntent(newAppPendingIntent);
        alarmNotificationBuilder.setAutoCancel(true);
        alarmNotificationBuilder.setSmallIcon(R.drawable.computericon);
        alarmNotificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        alarmNotificationBuilder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);
        alarmNotificationManager.notify(1234,alarmNotificationBuilder.build());

    }
}
