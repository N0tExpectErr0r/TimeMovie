package cn.n0texpecterr0r.timemovie.base.component.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;


/**
 * 时光电影中所有Activity的基类
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class TimeBaseActivity extends BaseActivity{
    private AlertDialog mLoadingDialog;

    protected void showLoading(String msg){
        showLoading(msg, false);
    }

    protected void showLoading(String msg, boolean cancelable) {
        if (mLoadingDialog == null)
            mLoadingDialog = new ProgressDialog.Builder(this)
                    .setMessage(msg)
                    .setCancelable(cancelable)
                    .create();
            mLoadingDialog.show();
    }

    protected void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}
