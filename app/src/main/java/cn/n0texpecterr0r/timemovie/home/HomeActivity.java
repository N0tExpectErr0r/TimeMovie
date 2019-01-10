package cn.n0texpecterr0r.timemovie.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.adapter.CommonPagerAdapter;
import cn.n0texpecterr0r.timemovie.base.bean.CommonTabBean;
import cn.n0texpecterr0r.timemovie.base.bus.EventBuser;
import cn.n0texpecterr0r.timemovie.base.component.activity.BaseActivity;
import cn.n0texpecterr0r.timemovie.event.LocationChangeEvent;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;
import cn.n0texpecterr0r.timemovie.location.view.LocationActivity;
import cn.n0texpecterr0r.timemovie.saling.view.SalingFragment;

public class HomeActivity extends BaseActivity {
    private TitleBar mTbTitle;
    private Location mLocation;
    private CommonTabLayout mTlTab;
    private ViewPager mVpContainer;
    private List<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        EventBuser.register(this);

        mTbTitle = findViewById(R.id.home_tb_title);
        mTlTab = findViewById(R.id.home_tl_tab);
        mVpContainer = findViewById(R.id.home_vp_container);

        mLocation = LocationManager.getInstance().getLocation();
        mTbTitle.setLeftTitle(mLocation.getName());
        mTbTitle.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                LocationActivity.startActivity(HomeActivity.this);
            }

            @Override
            public void onTitleClick(View v) {}

            @Override
            public void onRightClick(View v) {}
        });

        mFragmentManager = getSupportFragmentManager();
        mFragmentList = getFragmentList();
        initViewPager();
        initTabItems();
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SalingFragment());
        return fragments;
    }

    private void initViewPager() {
        CommonPagerAdapter adapter = new CommonPagerAdapter(mFragmentManager, mFragmentList);
        mVpContainer.setAdapter(adapter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTlTab.setCurrentTab(position);
                this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mVpContainer.setCurrentItem(0);
    }

    private void initTabItems() {
        List<CommonTabBean> tabBeans = getTabBeanList();
        ArrayList<CustomTabEntity> tabEntityList = new ArrayList<>(tabBeans);
        mTlTab.setTabData(tabEntityList);
        mTlTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVpContainer.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private List<CommonTabBean> getTabBeanList() {
        List<CommonTabBean> beans = new ArrayList<>();
        beans.add(new CommonTabBean("正在热映", 0,0));
        beans.add(new CommonTabBean("即将上映", 0, 0));
        return beans;
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
