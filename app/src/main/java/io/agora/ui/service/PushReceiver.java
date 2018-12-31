package io.agora.ui.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.parse.PLog;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import io.agora.largegroupcall.R;
import io.agora.largegroupcall.model.ConstantApp;
import io.agora.largegroupcall.ui.LiveRoom17Activity;
import io.agora.rtc.Constants;

public class PushReceiver extends ParsePushBroadcastReceiver {

    protected void onPushReceive(Context context, Intent intent) {

        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));

            String alert, title, roomName;
            alert = pushData.getString("alert");
            title = pushData.getString("title");
            roomName = pushData.getString("roomName");
            if (alert != null) {
                generateNotification(context, title, roomName);
            }
        } catch (JSONException var7) {
            PLog.e("ParsePushReceiver", "Unexpected JSONException when receiving push data: ", var7);
        }
    }

    private void generateNotification(Context context, String title, String roomName) {
        Intent intent = new Intent(context, LiveRoom17Activity.class);
        intent.putExtra(ConstantApp.ACTION_KEY_CROLE, Constants.CLIENT_ROLE_BROADCASTER);
        intent.putExtra(ConstantApp.ACTION_KEY_ROOM_NAME, roomName);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationManager mNotifM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title).setContentText("contenttext").setNumber(10);

        mBuilder.setContentIntent(contentIntent);
        mNotifM.notify(10, mBuilder.build());
    }

}
