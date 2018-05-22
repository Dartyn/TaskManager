package com.example.a16022667.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText etName, etDesc;
    Button btnSubmit, btnCancel;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etDesc = (EditText)findViewById(R.id.etDesc);
        etName = (EditText)findViewById(R.id.etName);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("default","Default Channel",NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(AddActivity.this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(AddActivity.this, "default");
                builder.setContentTitle(name);
                builder.setContentText(desc);
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Uri uri= RingtoneManager.getDefaultUri
                        (RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(uri);

                builder.setPriority(Notification.PRIORITY_HIGH);



                Notification n = builder.build();

                notificationManager.notify(1, n);
                finish();

                Intent intentAct = new Intent(AddActivity.this,MainActivity.class);
                intentAct.putExtra("name", name);
                intentAct.putExtra("desc",desc);
                startActivity(intentAct);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);


                int reqCode = 12345;
                PendingIntent pendingIntent = PendingIntent.getActivity(AddActivity.this, reqCode, intent,PendingIntent.FLAG_CANCEL_CURRENT);

                am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                DBHelper dbh = new DBHelper(AddActivity.this);
                long row_affected = dbh.insertName(name);
                long row_affected2 = dbh.insertDesc(desc);
                if (row_affected != -1){
                    Toast.makeText(AddActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }if (row_affected2 != -1){
                    Toast.makeText(AddActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
