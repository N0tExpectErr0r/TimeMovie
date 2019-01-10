package cn.n0texpecterr0r.timemovie.saling.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.bus.EventBuser;
import cn.n0texpecterr0r.timemovie.base.component.fragment.TimeMvpFragment;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.event.LocationChangeEvent;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;
import cn.n0texpecterr0r.timemovie.saling.SalingContract;
import cn.n0texpecterr0r.timemovie.saling.adapter.SalingAdapter;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import cn.n0texpecterr0r.timemovie.saling.presenter.SalingPresenter;
import cn.n0texpecterr0r.timemovie.saling.repo.RemoteSalingRepo;

/**
 * 正在售票Fragment
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public class SalingFragment extends TimeMvpFragment<SalingPresenter> implements SalingContract.View {
    private Location mLocation;
    private RecyclerView mRvList;
    private SalingAdapter mAdapter;
    private SwipeRefreshLayout mSrlRefresh;

    @Override
    protected void init(SalingPresenter presenter, Bundle saveInstanceState) {
        EventBuser.register(this);

        mRvList = (RecyclerView) findViewById(R.id.saling_rv_list);
        mSrlRefresh = (SwipeRefreshLayout) findViewById(R.id.saling_srl_refresh);

        mAdapter = new SalingAdapter(new ArrayList<>(), R.layout.item_saling,
                false, false);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);

        mLocation = LocationManager.getInstance().getLocation();
        presenter.fetchSalingMovies(mLocation.getId().intValue());
        mSrlRefresh.setRefreshing(true);

        mSrlRefresh.setOnRefreshListener(()->presenter.fetchSalingMovies(mLocation.getId().intValue()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBuser.unregister(this);
    }

    @Override
    protected SalingPresenter onCreatePresenter() {
        return new SalingPresenter(getContext(), this, new RemoteSalingRepo());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_saling;
    }

    @Override
    public void onLoadSaling(List<SalingMovie> movieList) {
        mAdapter.setDatas(movieList);
        mSrlRefresh.setRefreshing(false);
    }

    @Override
    public void onLoadError() {
        showToast("网络出现错误，请检查网络设置");
    }

    @Subscribe
    public void onLocationChanged(LocationChangeEvent event){
        mLocation = LocationManager.getInstance().getLocation();
        getPresenter().fetchSalingMovies(mLocation.getId().intValue());
        mSrlRefresh.setRefreshing(true);
    }
}
