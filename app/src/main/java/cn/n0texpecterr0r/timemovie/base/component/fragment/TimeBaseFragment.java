package cn.n0texpecterr0r.timemovie.base.component.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;

/**
 * 时光电影中所有Fragment的基类
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class TimeBaseFragment extends BaseFragment{
    private AlertDialog mLoadingDialog;

    protected void showLoading(String msg){
        showLoading(msg, false);
    }

    protected void showLoading(String msg, boolean cancelable) {
        boolean isDestroy = false;
        if(getActivity()!=null)
            isDestroy = getActivity().isFinishing();
        if (mLoadingDialog == null && !isDestroy)
            mLoadingDialog = new ProgressDialog.Builder(getContext())
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
