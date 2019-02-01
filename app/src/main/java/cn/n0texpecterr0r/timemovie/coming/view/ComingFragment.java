package cn.n0texpecterr0r.timemovie.coming.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.bus.EventBuser;
import cn.n0texpecterr0r.timemovie.base.component.fragment.TimeMvpFragment;
import cn.n0texpecterr0r.timemovie.coming.ComingContract;
import cn.n0texpecterr0r.timemovie.coming.adapter.ComingAdapter;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;
import cn.n0texpecterr0r.timemovie.coming.presenter.ComingPresenter;
import cn.n0texpecterr0r.timemovie.coming.repo.LocalComingRepo;
import cn.n0texpecterr0r.timemovie.coming.repo.RemoteComingRepo;
import cn.n0texpecterr0r.timemovie.detail.view.DetailActivity;
import cn.n0texpecterr0r.timemovie.event.LocationChangeEvent;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;

/**
 * 即将上映Fragment
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class ComingFragment extends TimeMvpFragment<ComingPresenter> implements ComingContract.View {
    private Location mLocation;
    private RecyclerView mRvList;
    private ComingAdapter mAdapter;
    private SwipeRefreshLayout mSrlRefresh;
    
    @Override
    protected void init(ComingPresenter presenter, Bundle saveInstanceState) {
        EventBuser.register(this);

        mRvList = (RecyclerView) findViewById(R.id.coming_rv_list);
        mSrlRefresh = (SwipeRefreshLayout) findViewById(R.id.coming_srl_refresh);

        mAdapter = new ComingAdapter(new ArrayList<>(), R.layout.item_coming,
                false, false);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((view, position) -> {
            ComingMovie movie = mAdapter.getDataAt(position);
            DetailActivity.actionStart(getContext(), movie.getId());
        });

        mLocation = LocationManager.getInstance().getLocation();
        presenter.fetchComingMovies(mLocation.getId().intValue());
        mSrlRefresh.setRefreshing(true);

        mSrlRefresh.setOnRefreshListener(()->presenter.fetchComingMovies(mLocation.getId().intValue()));
    }

    @Override
    protected ComingPresenter onCreatePresenter() {
        return new ComingPresenter(getContext(), this, new RemoteComingRepo(), new LocalComingRepo());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_coming;
    }

    @Override
    public void onLoadComing(List<ComingMovie> movieList) {
        mAdapter.setDatas(movieList);
        mSrlRefresh.setRefreshing(false);
    }

    @Override
    public void onLoadError() {
        showToast("网络出现错误，请检查网络设置");
        mSrlRefresh.setRefreshing(false);
    }

    @Subscribe
    public void onLocationChanged(LocationChangeEvent event){
        Log.d("ComingFragment", "location Changed!");
        mSrlRefresh.setRefreshing(true);
        mLocation = LocationManager.getInstance().getLocation();
        getPresenter().fetchComingMovies(mLocation.getId().intValue());
    }
}
