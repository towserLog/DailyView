package io.paizi.customview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import io.paizi.customview.R;

/**
 * Created by liubin on 2017/3/20.
 * google开源的流式布局
 */

public class FlexboxLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box);
        FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new FlexboxLayoutManager());
        recyclerView.setAdapter(new Adapter());
    }

    private void initData(String urlString, Map<String, String> params, Map<String, String> headers ){
        try {
            StringBuffer buf = new StringBuffer("?");

            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class Adapter extends RecyclerView.Adapter<VH>{
        int[] datas = {
                android.R.drawable.alert_dark_frame,
                android.R.drawable.alert_light_frame,
                android.R.drawable.arrow_down_float,
                android.R.drawable.arrow_up_float,
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_delete,
                android.R.drawable.ic_dialog_dialer,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_lock_idle_alarm,
                android.R.drawable.ic_lock_idle_lock,
                android.R.drawable.ic_lock_idle_low_battery,
                android.R.drawable.ic_lock_silent_mode,
                android.R.drawable.ic_lock_silent_mode_off
        };


        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {

            return new VH(new ImageView(FlexboxLayoutActivity.this));
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            ((ImageView)holder.itemView).setImageResource(datas[position]);
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }
    }

    public class VH extends RecyclerView.ViewHolder{

        public VH(ImageView itemView) {
            super(itemView);
        }
    }
}
