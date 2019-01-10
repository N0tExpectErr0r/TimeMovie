package cn.n0texpecterr0r.timemovie.saling.adapter;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseAdapter;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseViewHolder;
import cn.n0texpecterr0r.timemovie.base.image.ImageLoader;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;

/**
 * 正在销售的Adapter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public class SalingAdapter extends BaseAdapter<SalingMovie> {

    public SalingAdapter(List<SalingMovie> data, int itemLayoutId, boolean hasBanner, boolean canLoadMore) {
        super(data, itemLayoutId, hasBanner, canLoadMore);
    }

    @Override
    public void initItemView(BaseViewHolder holder, SalingMovie movie) {

        ImageView imageView = holder.getView(R.id.saling_iv_img);
        String imgUrl = movie.getImg();
        if (imgUrl != null) {
            ImageLoader.with(TimeApplication.getContext())
                    .load(imgUrl)
                    .placeholder(R.drawable.base_img_loading)
                    .error(R.drawable.base_img_loading)
                    .into(imageView);
        }else{
            imageView.setImageResource(R.drawable.base_img_loading);
        }

        holder.setText(R.id.saling_tv_name, movie.getNameCn());
        holder.setText(R.id.saling_tv_score, "评分："+ movie.getScore());
        holder.setText(R.id.saling_tv_director, "导演："+ movie.getDirector());
        holder.setText(R.id.saling_tv_actors, "主演："+movie.getActors());

        int visibility = movie.isHot()?View.VISIBLE:View.GONE;
        holder.setTextVisibility(R.id.saling_tv_hot, visibility);

        visibility = movie.is3D()?View.VISIBLE:View.GONE;
        holder.setTextVisibility(R.id.saling_tv_3d, visibility);

        visibility = movie.isDMAX()?View.VISIBLE:View.GONE;
        holder.setTextVisibility(R.id.saling_tv_dmax, visibility);

        visibility = movie.isHasTrailer()?View.VISIBLE:View.GONE;
        holder.setTextVisibility(R.id.saling_tv_trailer, visibility);

        visibility = movie.isIMAX()?View.VISIBLE:View.GONE;
        holder.setTextVisibility(R.id.saling_tv_imax, visibility);

        visibility = movie.isIMAX3D()?View.VISIBLE:View.GONE;
        holder.setTextVisibility(R.id.saling_tv_imax3d, visibility);
    }
}
