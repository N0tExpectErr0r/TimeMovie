package cn.n0texpecterr0r.timemovie.base.component.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;


import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;

/**
 * 时光电影的MvpActivity
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class TimeMvpActivity<P extends BaseContract.Presenter> extends TimeBaseActivity implements BaseContract.View{
    protected P mPresenter;
    private Lifecycle mLifecycle;

    @Override
    final protected void init(Bundle savedInstanceState) {
        mLifecycle = new LifecycleRegistry(this);
        mPresenter = onCreatePresenter();
        init(mPresenter, savedInstanceState);
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }

    abstract protected void init(P presenter, Bundle savedInstanceState);

    protected abstract P onCreatePresenter();

    final public P getPresenter() {
        return mPresenter;
    }
}
