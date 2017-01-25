package io.paizi.supportview.app;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.paizi.myutils.LogUtil;

/**
 * Created by pai on 2016/12/14.
 *
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化presco
        Fresco.initialize(this);
        //抓全全局异常写入到文件中
//        Thread.currentThread().setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }

    class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LogUtil.print(LogUtil.printInfo()
                    , "uncaughtException"
                    , System.currentTimeMillis() + "");
            //将日志输出到控制台
            e.printStackTrace();
            //将异常输出到文件
            e.printStackTrace(LogUtil.getPrintStream());
            //当有异常的时候自己吧自己杀死 闪退！
//            Process.killProcess(Process.myPid());
            Toast.makeText(getApplicationContext(), "发现异常，自动退出", Toast.LENGTH_LONG).show();
            System.exit(0);
        }
    }

}
