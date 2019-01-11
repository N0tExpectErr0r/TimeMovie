package cn.n0texpecterr0r.timemovie.coming.adapter;

import android.view.View;
import android.widget.ImageView;

import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseAdapter;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseViewHolder;
import cn.n0texpecterr0r.timemovie.base.image.ImageLoader;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;

/**
 * 即将上映Adapter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class ComingAdapter extends BaseAdapter<ComingMovie> {
    public ComingAdapter(List<ComingMovie> data, int itemLayoutId, boolean hasBanner, boolean canLoadMore) {
        super(data, itemLayoutId, hasBanner, canLoadMore);
    }

    @Override
    public void initItemView(BaseViewHolder holder, ComingMovie movie) {
        ImageView imageView = holder.getView(R.id.coming_iv_img);
        String imgUrl = movie.getImg();
        if (imgUrl != null) {
            ImageLoader.with(TimeApplication.getContext())
                    .resize(400,225)
                    .load(imgUrl)
                    .placeholder(R.drawable.base_img_loading)
                    .error(R.drawable.base_img_loading)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.base_img_loading);
        }

        holder.setText(R.id.coming_tv_name, movie.getName());

        if (movie.getWantedCount() > 0) {
            holder.setText(R.id.coming_tv_want, movie.getWantedCount() + "人想看");
        }else {
            holder.setText(R.id.coming_tv_want, "暂无数据");
        }

        if (!movie.getType().isEmpty()) {
            holder.setTextVisibility(R.id.coming_tv_type, View.VISIBLE);
            holder.setText(R.id.coming_tv_type, movie.getType());
        }else {
            holder.setTextVisibility(R.id.coming_tv_type, View.GONE);
        }

        holder.setText(R.id.coming_tv_director, "导演：" + movie.getDirector());
        holder.setText(R.id.coming_tv_actors, "主演：" + movie.getActor1()+" / "+movie.getActor2());
        holder.setText(R.id.coming_tv_date, movie.getReleaseDate());

        int visibility = movie.isVideo() ? View.VISIBLE : View.GONE;
        holder.setTextVisibility(R.id.coming_tv_video, visibility);

    }
}
