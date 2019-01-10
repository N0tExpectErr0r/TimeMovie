package cn.n0texpecterr0r.timemovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import cn.n0texpecterr0r.timemovie.base.component.activity.TimeBaseActivity;
import cn.n0texpecterr0r.timemovie.base.util.Timer;
import cn.n0texpecterr0r.timemovie.home.HomeActivity;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;
import cn.n0texpecterr0r.timemovie.location.view.LocationActivity;

import static cn.n0texpecterr0r.timemovie.location.manager.LocationManager.SP_LOCATION;

public class LauncherActivity extends TimeBaseActivity {
    private TextView mTvSkip;
    private Timer mTimer;

    @Override
    protected void init(Bundle savedInstanceState) {
        mTvSkip = findViewById(R.id.launcher_tv_skip);

        mTimer = new Timer().startTimer(10000, this::navigateActivity);

        mTvSkip.setOnClickListener((view)->{
            mTimer.cancelAll();
            navigateActivity();
        });
    }

    private void navigateActivity() {
        if (LocationManager.getInstance().getLocation() == null){
            // 还未定位，跳转定位Activity
            LocationActivity.startActivity(this);
        }else{
            // 已经定位，跳转主界面
            HomeActivity.startActivity(this);
        }
        finish();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_launcher;
    }
}
