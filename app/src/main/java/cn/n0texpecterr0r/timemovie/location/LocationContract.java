package cn.n0texpecterr0r.timemovie.location;

import android.content.Context;

import java.util.List;

import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.repo.LocalLocationRepo;
import cn.n0texpecterr0r.timemovie.location.repo.RemoteLocationRepo;

/**
 * Location的Mvp契约类
 *
 * @author N0tExpectErr0r
 * @time 2019/01/09
 */
public interface LocationContract {
    interface View extends BaseContract.View{
        void onLoadLocation(List<Location> locations);

        void onLoadError();
    }

    abstract class Presenter extends BaseContract.RepoPresenter<View,RemoteLocationRepo,LocalLocationRepo> {

        public Presenter(Context context, View view, RemoteLocationRepo remote, LocalLocationRepo local) {
            super(context, view, remote, local);
        }

        public abstract void fetchLocations();

        public abstract void searchLocations(String keyword);
    }
}
