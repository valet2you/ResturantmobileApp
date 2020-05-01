package com.viralops.touchlessfoodordering.ui.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.viralops.touchlessfoodordering.MainActivity;
import com.viralops.touchlessfoodordering.R;
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


            Intent intent = new Intent(FirebaseMessagingService.this, MainActivity.class);
            intent.putExtra("key", "value");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(FirebaseMessagingService.this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);





        int notifyID = 1;
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "VServe";// The user-visible name of the channel.
        Notification notification;
        int importance = NotificationManager.IMPORTANCE_HIGH;
// Create a notification and set the notification channel.
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher).copy(Bitmap.Config.ARGB_8888, true);
        NotificationChannel mChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notification = new Notification.Builder(FirebaseMessagingService.this)
                    .setContentTitle("VServe ++ - New Request Found upar!!!")
                    .setContentText(messageBody)
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.ic_launcher)

                    .setAutoCancel(true)
                    .setChannelId(CHANNEL_ID).setContentIntent(pendingIntent).build();
        }
        else
        {
            notification = new Notification.Builder(FirebaseMessagingService.this)
                    .setContentTitle("VServe ++ - New Request Found !!!")
                    .setContentText(messageBody)
                    .setLargeIcon(bm)

                    .setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true)
                    .setContentIntent(pendingIntent).build();

        }
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(mChannel);
            mNotificationManager.notify(notifyID , notification);
            mNotificationManager.cancel(notifyID);
            // startForeground(1,notification);

        }
        else{
            mNotificationManager.notify(notifyID, notification);
            mNotificationManager.cancel(notifyID);
            //startForeground(1,notification);

            //   mNotificationManager.cancelAll()
        }

    }




    static void updateMyActivity(Context context, String message) {

        Intent intent = new Intent("com.viralops.touchlessfoodordering");
        context.sendBroadcast(intent);
    }
}
