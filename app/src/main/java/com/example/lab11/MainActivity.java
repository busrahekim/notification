package com.example.lab11;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import static com.example.lab11.App.CHANNEL_1_ID;
import static com.example.lab11.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    EditText messageEdit, titleEdit;
    Button sendChannel1, sendChannel2;
    NotificationManagerCompat notificationManagerCompat;
    String title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEdit = findViewById(R.id.titleEdit);
        messageEdit = findViewById(R.id.messageEdit);
        sendChannel1 = findViewById(R.id.sendChannel1);
        sendChannel2 = findViewById(R.id.sendChannel2);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        title = titleEdit.getText().toString();
        message = messageEdit.getText().toString();



    }

    public void sendOnChannel1(View v)
    {

        title = titleEdit.getText().toString();
        message = messageEdit.getText().toString();

        Intent activityIntent = new Intent(this, com.example.lab11.MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);


        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    public void sendOnChannel2(View v)
    {
        title = titleEdit.getText().toString();
        message = messageEdit.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManagerCompat.notify(2, notification);
    }

}