package io.paizi.supportview.ui.activity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paizi.supportview.R;
import io.paizi.supportview.app.BaseActivity;

/**
 * Created by pai on 2017/2/21.
 *
 */

public class MediaPlayerActivity extends BaseActivity {
    private static final String TAG = "mediatest.Main";

    MediaPlayer mediaPlayer;

    @BindView(R.id.surface_view)
    SurfaceView mSurfaceView;

//    @BindView(R.id.video_view)
//    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        ButterKnife.bind(this);

        findViewById(R.id.start_button).setOnClickListener(clickListener);
        findViewById(R.id.pause_button).setOnClickListener(clickListener);

        initMediaPlayer();
        initSurfaceView();

//        videoView.setVideoPath(videoSrc);
//        videoView.start();
    }

    private void initMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnCompletionListener(completionListener);
        mediaPlayer.setOnErrorListener(errorListener);
        mediaPlayer.setOnPreparedListener(preparedListener);
    }

    private void initSurfaceView(){
        //保持屏幕常亮
        mSurfaceView.getHolder().setKeepScreenOn(true);
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceView.getHolder().addCallback(callBack);
    }

    /**
     * @param url 文件路径， 需要包含协议
     */
    private void playMusic(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param resName 播放asset中的文件
     */
    private void playAssetsMusic(String resName) {
        try {
            AssetFileDescriptor fileDescripter = getAssets().openFd(resName);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(fileDescripter.getFileDescriptor(), fileDescripter.getStartOffset(), fileDescripter.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playVideo(String videoSrc){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videoSrc);
            mediaPlayer.prepareAsync();
            mediaPlayer.setDisplay(mSurfaceView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start_button:
                    playVideo(videoSrc);
//                    playMusic(musicSrc);
//                    playAssetsMusic("jazz_ver-67.4-132.6.mp3");
                    break;
                case R.id.pause_button:
                    mediaPlayer.pause();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 准备完成的监听
     * 配合prepareAsync()食用
     */
    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
        }
    };

    /**
     * surface生命周期
     * 所对应的回调
     */
    SurfaceHolder.Callback callBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }
    };

    MediaPlayer.OnBufferingUpdateListener onBufferingUpdateLinsener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {

        }
    };

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.i(TAG, "onCompletion");
        }
    };

    MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Log.i(TAG, "onError");
            return false;
        }
    };

    private String musicSrc = "https://al-qn-echo-cp-cdn.app-echo.com/c2_96k/ac0fdc0cb20e15435ca713a0aba9f3af362533ea8f5a0a385695c07ad0fef606eae1f6c3.mp3?1455096918";

    private String videoSrc = "http://124.14.10.110/xdispatch/7xir7t.com1.z0.glb.clouddn.com/music_festival_1207.mp4";
}
