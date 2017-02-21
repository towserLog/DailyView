package io.paizi.supportview.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import io.paizi.supportview.app.BaseActivity;
import io.paizi.supportview.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view){
        switch (view.getId()){
            case R.id.drag_top:
                jump(DragTopActivity.class);
                break;
            case R.id.swipe_recycler_view:
                jump(RecycleViewActicity.class);
                break;
            case R.id.login_activity:
                jump(LoginActivity.class);
                break;
            case R.id.mediaplayr_activity:
                jump(MediaPlayerActivity.class);
                break;
            default:
                break;
        }
    }

    private void jump(Class<? extends Activity> clazz){
        startActivity(new Intent(this, clazz));
    }
}
