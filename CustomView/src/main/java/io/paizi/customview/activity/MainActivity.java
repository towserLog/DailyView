package io.paizi.customview.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.paizi.customview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view){
        switch (view.getId()){
            case R.id.head_recycleview:
                jump(HeadRecycleActivity.class);
                break;
            default:
                break;
        }
    }

    private void jump(Class<? extends Activity> clazz){
        startActivity(new Intent(this, clazz));
    }
}
