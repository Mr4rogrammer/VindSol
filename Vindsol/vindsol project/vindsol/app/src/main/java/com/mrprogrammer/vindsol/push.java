package com.mrprogrammer.vindsol;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class push extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull @NotNull String s) {

        super.onNewToken(s);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        String title=remoteMessage.getNotification().getTitle();
        String msg=remoteMessage.getNotification().getBody();
        String img=remoteMessage.getNotification().getIcon();

        String ID="HEADS_UP_NOTIFICATION";

        NotificationChannel channel= new NotificationChannel(ID,"Heads Up Notification",NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this,ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(false);
        NotificationManagerCompat.from(this).notify(1,notification.build());


        //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

        super.onMessageReceived(remoteMessage);

    }
}
