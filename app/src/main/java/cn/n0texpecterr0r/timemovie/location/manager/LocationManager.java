package cn.n0texpecterr0r.timemovie.location.manager;

import android.content.Context;
import android.content.SharedPreferences;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.location.bean.Location;

public class LocationManager {
    public static final String SP_LOCATION = "SP_LOCATION";

    private LocationManager() {
    }

    private static class SingletonInstance {
        private static final LocationManager INSTANCE = new LocationManager();
    }

    public static LocationManager getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public void saveLocation(Location location){
        SharedPreferences.Editor editor = TimeApplication.getContext().
                getSharedPreferences(SP_LOCATION, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.putInt("count",location.getCount());
        editor.putLong("id", location.getId());
        editor.putString("name", location.getName());
        editor.putString("pinyinFull", location.getPinyinFull());
        editor.putString("pinyinShort", location.getPinyinShort());
        editor.apply();
    }

    public Location getLocation(){
        SharedPreferences sp = TimeApplication.getContext()
                .getSharedPreferences(SP_LOCATION, Context.MODE_PRIVATE);
        Location location = new Location();
        location.setCount(sp.getInt("count",0));
        location.setId(sp.getLong("id", 0));
        location.setName(sp.getString("name", "null"));
        location.setPinyinFull(sp.getString("pinyinFull", null));
        location.setPinyinShort(sp.getString("pinyinShort", null));
        if(location.getId() == 0)
            return null;
        return location;
    }
}