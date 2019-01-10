package cn.n0texpecterr0r.timemovie.base.component.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

import com.uber.autodispose.AutoDisposeConverter;

import java.lang.ref.WeakReference;

import cn.n0texpecterr0r.timemovie.base.component.lifecycle.AutoDisposer;
import cn.n0texpecterr0r.timemovie.base.component.lifecycle.LifecycleFullObserver;

/**
 * Mvp契约类，基于Lifecycle
 *
 * @author N0tExpectErr0r
 * @time 2018/11/23
 */
public interface BaseContract {

    interface View extends LifecycleOwner {
    }

    abstract class Presenter<V extends View> implements LifecycleFullObserver {
        private WeakReference<V> view;
        private Context context;

        public Presenter(Context context, V view) {
            bindLife(view);
            this.context = context.getApplicationContext();
        }

        public V getView() {
            return (view != null && view.get() != null) ? view.get() : null;
        }

        public Context getAppContext() {
            return context;
        }

        private void bindLife(V view) {
            view.getLifecycle().addObserver(this);
            this.view = new WeakReference<>(view);
        }

        protected <T> AutoDisposeConverter<T> bindLifecycle() {
            return AutoDisposer.bind(view.get());
        }


        @Override
        public void onLifecycleAttach() {

        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onResume() {
        }

        @Override
        public void onPause() {
        }

        @Override
        public void onStop() {
        }

        @Override
        public void onDestroy() {
            detachView();
        }

        @Override
        public void onAny() {
            if (getView() != null) {
                onAny(getView().getLifecycle().getCurrentState());
            }
        }

        protected void onAny(Lifecycle.State state) {
        }

        public void onDetachView() {
        }

        public void detachView() {
            onDetachView();
            if (view != null) {
                view.clear();
                view = null;
            }
        }
    }

    abstract class BaseRepoPresenter<V extends View, R extends Repository> extends Presenter<V> {

        private R repository;

        public R getRepository() {
            return repository;
        }

        public BaseRepoPresenter(Context context, V view, R repository) {
            super(context, view);
            this.repository = repository;
        }
    }

    abstract class RepoPresenter<V extends View, R extends Repository, L extends Repository> extends Presenter<V>{
        private R remoteRepository;
        private L localRepository;

        public R getRemoteRepository() {
            return remoteRepository;
        }

        public L getLocalRepository() {
            return localRepository;
        }

        public RepoPresenter(Context context, V view, R remote, L local) {
            super(context, view);
            this.remoteRepository = remote;
            this.localRepository = local;
        }
    }

    interface Repository {

    }
}
