package cn.n0texpecterr0r.timemovie.location.adapter;

import android.util.Log;

import java.util.AbstractSequentialList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseAdapter;
import cn.n0texpecterr0r.timemovie.base.adapter.BaseViewHolder;
import cn.n0texpecterr0r.timemovie.location.bean.Location;

/**
 * 定位Adapter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/09
 */
public class LocationAdapter extends BaseAdapter<Location> {
    private Map<Character, Integer> mCharIndexs;

    public LocationAdapter(List<Location> data, int itemLayoutId, boolean hasBanner) {
        super(data, itemLayoutId, hasBanner, false);
        mCharIndexs = new HashMap<>();
    }

    @Override
    public void initItemView(BaseViewHolder holder, Location location) {
        holder.setText(R.id.location_tv_name, location.getName());
    }

    @Override
    public void setDatas(List<Location> datas) {
        Collections.sort(datas, (location1, location2)-> {
            String loc1 = location1.getPinyinFull().toUpperCase();
            String loc2 = location2.getPinyinFull().toUpperCase();
            return loc1.compareTo(loc2);
        });
        mCharIndexs.clear();
        // 将首字母放入index的map
        for (int i = 0; i < datas.size(); i++) {
            Location location = datas.get(i);
            char ch = location.getPinyinFull().toUpperCase().charAt(0);
            if (ch >= 'A' && ch <= 'Z') {
                if (mCharIndexs.get(ch) == null) {
                    mCharIndexs.put(ch, i);
                }
            } else {
                if (mCharIndexs.get('#') == null) {
                    mCharIndexs.put('#', i);
                }
            }
        }
        super.setDatas(datas);
    }

    public Integer getIndexOfChar(char ch) {
               return mCharIndexs.get(ch);
    }

    public boolean isFirstPosition(int position){
        return mCharIndexs.containsValue(position);
    }

    public String getGroupTitle(int position){
        return mDatas.get(position).getPinyinFull().toUpperCase().charAt(0)+"";
    }

    public Location getData(int position){
        return mDatas.get(position);
    }
}
