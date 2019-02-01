package cn.n0texpecterr0r.timemovie.location.view;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseAdapter;
import cn.n0texpecterr0r.timemovie.base.bus.EventBuser;
import cn.n0texpecterr0r.timemovie.base.component.activity.TimeMvpActivity;
import cn.n0texpecterr0r.timemovie.event.LocationChangeEvent;
import cn.n0texpecterr0r.timemovie.location.LocationContract;
import cn.n0texpecterr0r.timemovie.location.adapter.LocationAdapter;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;
import cn.n0texpecterr0r.timemovie.location.presenter.LocationPresenter;
import cn.n0texpecterr0r.timemovie.location.repo.LocalLocationRepo;
import cn.n0texpecterr0r.timemovie.location.repo.RemoteLocationRepo;
import cn.n0texpecterr0r.timemovie.widget.SideBar;
import cn.n0texpecterr0r.timemovie.widget.SideBarLayout;
import cn.n0texpecterr0r.timemovie.widget.StickyDecoration;

public class LocationActivity extends TimeMvpActivity<LocationPresenter>
        implements LocationContract.View, BaseAdapter.OnItemClickListener {
    private RecyclerView mRvList;
    private LocationAdapter mAdapter;
    private SwipeRefreshLayout mSrlRefresh;
    private SideBarLayout mSblSidebar;
    private TitleBar mTbTitle;
    private EditText mEtSearch;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, LocationActivity.class));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_location;
    }

    @Override
    protected void init(LocationPresenter presenter, Bundle savedInstanceState) {
        mRvList = findViewById(R.id.location_rv_list);
        mSrlRefresh = findViewById(R.id.location_srl_refresh);
        mSblSidebar = findViewById(R.id.location_sbl_sidebar);
        mTbTitle = findViewById(R.id.location_tb_title);
        mEtSearch = findViewById(R.id.location_et_search);

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LocationAdapter(new ArrayList<>(),R.layout.item_location, false);
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        // 开始加载
        presenter.fetchLocations();
        mSrlRefresh.setRefreshing(true);
        mSrlRefresh.setOnRefreshListener(presenter::fetchLocations);

        mRvList.addItemDecoration(new StickyDecoration(this, new StickyDecoration.ISticky() {
            @Override
            public boolean isFirstPosition(int pos) {
                return mAdapter.isFirstPosition(pos);
            }

            @Override
            public String getGroupTitle(int pos) {
                return mAdapter.getGroupTitle(pos);
            }
        }));


        mSblSidebar.setOnChooseLetterListener(new SideBar.OnChooseLetterListener() {
            @Override
            public void onChoose(String letter) {
                LinearLayoutManager manager = (LinearLayoutManager) mRvList.getLayoutManager();
                Integer index = mAdapter.getIndexOfChar(letter.charAt(0));
                if (index!=null){
                    manager.scrollToPositionWithOffset(index, 0);
                    manager.setStackFromEnd(false);
                }
            }

            @Override
            public void onCancel() {}
        });

        mTbTitle.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                presenter.searchLocations(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    protected LocationPresenter onCreatePresenter() {
        return new LocationPresenter(this,this,
                new RemoteLocationRepo(), new LocalLocationRepo());
    }

    @Override
    public void onLoadLocation(List<Location> locations) {
        mAdapter.setDatas(locations);
        mSrlRefresh.setRefreshing(false);
        mSrlRefresh.setEnabled(false);
    }

    @Override
    public void onLoadError() {
        showToast("网络出现错误，请检查网络设置");
        mSrlRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        Location loc = mAdapter.getData(position);
        new AlertDialog.Builder(this)
                .setTitle("选择城市")
                .setMessage("确定切换城市到 "+loc.getName()+" ?")
                .setPositiveButton("确定", (di, i)->{
                    LocationManager.getInstance().saveLocation(loc);
                    // 前往主界面
                    EventBuser.getInstance().post(new LocationChangeEvent());
                    finish();
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}
