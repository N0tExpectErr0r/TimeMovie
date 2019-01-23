package cn.n0texpecterr0r.timemovie.base.component.fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;


import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;

/**
 * 时光电影的MvpFragment
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class TimeMvpFragment<P extends BaseContract.Presenter> extends TimeBaseFragment implements BaseContract.View{
    protected P mPresenter;
    private Lifecycle mLifecycle;

    public TimeMvpFragment(){
        mLifecycle = new LifecycleRegistry(this);
    }

    @Override
    final protected void init(Bundle savedInstanceState) {
        mPresenter = onCreatePresenter();
        init(mPresenter, savedInstanceState);
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }

    abstract protected void init(P presenter, Bundle saveInstanceState);

    protected abstract P onCreatePresenter();

    public P getPresenter() {
        return mPresenter;
    }

}
