package cn.n0texpecterr0r.timemovie.detail.adapter;

import android.widget.ImageView;

import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseAdapter;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseViewHolder;
import cn.n0texpecterr0r.timemovie.base.image.ImageLoader;
import cn.n0texpecterr0r.timemovie.detail.bean.Actor;

/**
 * 演员列表 Adapter
 *
 * @author N0tExpectErr0r
 * @time 2019/02/01
 */
public class ActorAdapter extends BaseAdapter<Actor> {
    public ActorAdapter(List<Actor> data, int itemLayoutId, boolean hasBanner, boolean canLoadMore) {
        super(data, itemLayoutId, hasBanner, canLoadMore);
    }

    @Override
    public void initItemView(BaseViewHolder holder, Actor actor) {
        ImageView img = holder.getView(R.id.actor_iv_img);
        ImageLoader.with(TimeApplication.getContext())
                .load(actor.getImg())
                .placeholder(R.drawable.base_img_loading)
                .error(R.drawable.base_img_loading)
                .into(img);

        holder.setText(R.id.actor_tv_name, actor.getName());
        holder.setText(R.id.actor_tv_roleName, "饰："+ actor.getRoleName());
    }
}
