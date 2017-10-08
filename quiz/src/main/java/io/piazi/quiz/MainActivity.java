package io.piazi.quiz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Messenger mMessengerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        messengerIPC();
        aidlIPC();
    }
    //使用Messenger实现IPC (Messenger底层也是使用的Binder机制)
    private void messengerIPC() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mMessengerConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mMessengerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessengerService = new Messenger(service);
            Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello, this is client");
            msg.setData(bundle);
            //将客户端的messenger 发送到服务端，服务端向客户端发送数据会用到
            msg.replyTo = messenger;
            try {
                // 给binder设置死亡代理
                service.linkToDeath(mDeathRecipient, 0);
                mMessengerService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private final Messenger messenger = new Messenger(new MessengerHandler());
    public static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.MSG_FROM_SERVER:
                    Log.i(TAG, "receive msg form server:"+msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    //使用AIDL实现IPC
    private void aidlIPC() {
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, mMusicConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mMusicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMusicManager musicManager = IMusicManager.Stub.asInterface(service);
            try {
                List<Music> mMusicList = musicManager.getMusicList();
                Log.i(TAG, mMusicList.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //远程服务的死亡代理，可以监听服务端的Binder连接断裂(即Binder死亡)
    Binder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mMessengerService.getBinder().unlinkToDeath(mDeathRecipient, 0);
            Log.i(TAG, "远程服务已死亡");
        }
    };
}
