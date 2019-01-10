package cn.n0texpecterr0r.timemovie.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import org.greenrobot.eventbus.Subscribe;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.bus.EventBuser;
import cn.n0texpecterr0r.timemovie.base.component.activity.BaseActivity;
import cn.n0texpecterr0r.timemovie.event.LocationChangeEvent;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;
import cn.n0texpecterr0r.timemovie.location.view.LocationActivity;

public class HomeActivity extends BaseActivity {
    private TitleBar mTbTitle;
    private Location mLocation;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBuser.register(this);

        mTbTitle = findViewById(R.id.home_tb_title);

        mLocation = LocationManager.getInstance().getLocation();

        mTbTitle.setLeftTitle(mLocation.getName());
        mTbTitle.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                LocationActivity.startActivity(HomeActivity.this);
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBuser.unregister(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Subscribe
    public void onLocationChanged(LocationChangeEvent event){
        mLocation = LocationManager.getInstance().getLocation();
        mTbTitle.setLeftTitle(mLocation.getName());
    }
}
