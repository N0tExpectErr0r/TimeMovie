package cn.n0texpecterr0r.timemovie.coming.view;

import android.os.Bundle;

import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.component.fragment.TimeMvpFragment;
import cn.n0texpecterr0r.timemovie.coming.ComingContract;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;
import cn.n0texpecterr0r.timemovie.coming.presenter.ComingPresenter;
import cn.n0texpecterr0r.timemovie.coming.repo.RemoteComingRepo;

/**
 * 即将上映Fragment
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class ComingFragment extends TimeMvpFragment<ComingPresenter> implements ComingContract.View {
    @Override
    protected void init(ComingPresenter presenter, Bundle saveInstanceState) {

    }

    @Override
    protected ComingPresenter onCreatePresenter() {
        return new ComingPresenter(getContext(), this, new RemoteComingRepo());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_coming;
    }

    @Override
    public void onLoadComing(List<ComingMovie> movieList) {

    }

    @Override
    public void onLoadError() {

    }
}
