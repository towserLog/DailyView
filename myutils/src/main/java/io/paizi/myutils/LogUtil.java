package io.paizi.myutils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pai on 2017/1/15.
 * 日志输出类，可以将日志输出到本地文件中
 * 可以输出当前时间，行号，以及信息
 */
public class LogUtil {

    private static FileOutputStream fos;
    private static File file;
    private static SimpleDateFormat simpleDateFormat;

    private static String fileName = "LogFile";


    /**
     * 初始化文件名以及日期格式
     */
    public static void init(){
        simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINESE);
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                + File.separator+ fileName+".txt");
        if(!file.exists()){
            Log.i("file", file.getParentFile().mkdirs()+"");
            try {
                Log.i("file", file.createNewFile()+"");
//                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(file.isDirectory()){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            System.setErr(new PrintStream(new FileOutputStream(file, true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return 返回调用位置的信息
     * 所在的类名，方法名，以及行号
     */
    public static String printInfo(){
        StackTraceElement[] trace = new Throwable().getStackTrace();
        // 下标为0的元素是上一行语句的信息, 下标为1的才是调用printLine的地方的信息
        StackTraceElement tmp = trace[1];
        String info =  tmp.getClassName() + "." + tmp.getMethodName()
                + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + ")\r\n";
        System.out.println(info);
        return info;
    }

    /**
     * 输出日志到文件
     * @param info  调用{@link LogUtil#printInfo()}来获得方法名以及行号
     * @param tag   打个标记
     * @param msg   输出的信息
     */
    public static void print(String info, String tag, String msg){

        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\r\n*****************************************\r\n");
            stringBuffer.append(simpleDateFormat.format(new Date()));
            stringBuffer.append("\r\n");
            stringBuffer.append(info);
            stringBuffer.append("\r\n");
            stringBuffer.append(tag);
            stringBuffer.append("\r\n");
            stringBuffer.append(msg);
            stringBuffer.append("\r\n");
            stringBuffer.append("\r\n");
            stringBuffer.append("\r\n");
//            String data = simpleDateFormat.format(new Date())+" -> "+tag+":"+line+"\r\n"+ name+":"+value+"\r\n\r\n";
//            data.getBytes(Charset.forName("utf-8"));
            fos = new FileOutputStream(file, true);
//            fos.write(data.getBytes(Charset.forName("utf-8")));
            fos.write(stringBuffer.toString().getBytes(Charset.forName("utf-8")));
            fos.close();
        }catch (IOException e) {
//            print("exception", e.toString());
            e.printStackTrace();
        }
    }

    /**
     * @return 返回一个PrintStream
     *
     */
    public static PrintStream getPrintStream(){
        try {
            fos = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new PrintStream(fos, true);
    }
}

