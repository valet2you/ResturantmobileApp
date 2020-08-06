package com.viralops.touchlessfoodordering.ui.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.viralops.touchlessfoodordering.R;
import com.viralops.touchlessfoodordering.ui.Associate.AssociateMain;
import com.viralops.touchlessfoodordering.ui.Support.SessionManager;
import com.viralops.touchlessfoodordering.ui.Support.SessionManagerFCM;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    SessionManager sessionManager;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        SessionManagerFCM pref = new SessionManagerFCM(getApplicationContext());
        pref.setToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> params = remoteMessage.getData();
        JSONObject object = new JSONObject(params);
        updateMyActivity(this,"df");
        String target="";
        String message="";
        sessionManager=new SessionManager(this);
        try {
            message=object.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }




           sendNotification(message);

    }
    private void sendNotification(String messageBody) {

        Bitmap logo = null;
        try {

            Drawable myDrawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
            logo = ((BitmapDrawable) myDrawable).getBitmap();
        } catch (Exception ignored) {
        }
        PendingIntent pendingIntent;


            Intent intent = new Intent(FirebaseMessagingService.this, AssociateMain.class);
            intent.putExtra("key", "value");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(FirebaseMessagingService.this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);





        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification n = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                n = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setSound(uri)
                        .setChannelId("channel")
                        .setAutoCancel(true).build();
            }
            else{
                n = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setSound(uri)
                        .setAutoCancel(true).build();
            }
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify((int) System.currentTimeMillis(), n);

            e.printStackTrace();
        }


    }




    static void updateMyActivity(Context context, String message) {

        Intent intent = new Intent("com.viralops.touchlessfoodordering");
        context.sendBroadcast(intent);
    }
}
