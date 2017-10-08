package io.piazi.quiz;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by towse on 2017/10/7.
 */

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private final Messenger messenger = new Messenger(new MessengerHandler());
    public static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MSG_FROM_CLIENT:
                    Log.i(TAG, "receive msg form Client:"+msg.getData().getString("msg"));
                    sendToClient(msg);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private static void sendToClient(Message msg) {
        Messenger client = msg.replyTo;
        Message replyMessage = Message.obtain(null, Constant.MSG_FROM_SERVER);
        Bundle bundle = new Bundle();
        bundle.putString("reply", "hello, this is server");
        replyMessage.setData(bundle);
        try {
            client.send(replyMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
