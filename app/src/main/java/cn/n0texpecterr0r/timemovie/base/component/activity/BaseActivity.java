package cn.n0texpecterr0r.timemovie.base.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.n0texpecterr0r.timemovie.base.util.HandlerUtil;
import cn.n0texpecterr0r.timemovie.base.util.ToastUtil;

/**
 * Activity基类，所有Activity均继承自它
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected ToastUtil mToastUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (mToastUtil == null) mToastUtil = new ToastUtil(this);
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getContentViewId();

    protected void showToast(String msg) {
        HandlerUtil.runOnUiThread(() -> {
            if (mToastUtil == null)
                mToastUtil = new ToastUtil(this);
            mToastUtil.showToast(msg);
        });
    }
}
