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

import com.google.firebase.messaging.RemoteMessage;
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

        if(message.equals("Add order to queue")){
            try {
                target=object.getString("queued_orders");
                sessionManager.setSLOT1(target);
                sessionManager.setNotificationvalue("queue");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else{
            try {
                message=object.getString("message");
                //target=object.getString("queued_orders");
                sessionManager.setSLOT1("");
                sessionManager.setNotificationvalue("dashboard");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }




    static void updateMyActivity(Context context, String message) {

        Intent intent = new Intent("valley2you.ird");
        context.sendBroadcast(intent);
    }
}
