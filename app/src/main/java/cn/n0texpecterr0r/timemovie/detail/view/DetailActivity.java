package cn.n0texpecterr0r.timemovie.detail.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.zhouwei.blurlibrary.EasyBlur;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.n0texpecterr0r.timemovie.R;
import cn.n0texpecterr0r.timemovie.base.component.activity.TimeMvpActivity;
import cn.n0texpecterr0r.timemovie.base.image.ImageLoader;
import cn.n0texpecterr0r.timemovie.detail.DetailContract;
import cn.n0texpecterr0r.timemovie.detail.adapter.ActorAdapter;
import cn.n0texpecterr0r.timemovie.detail.adapter.StageImgAdapter;
import cn.n0texpecterr0r.timemovie.detail.bean.MovieDetail;
import cn.n0texpecterr0r.timemovie.detail.bean.StageImg;
import cn.n0texpecterr0r.timemovie.detail.presenter.DetailPresenter;
import cn.n0texpecterr0r.timemovie.detail.repo.LocalDetailRepo;
import cn.n0texpecterr0r.timemovie.detail.repo.RemoteDetailRepo;
import cn.n0texpecterr0r.timemovie.location.manager.LocationManager;

public class DetailActivity extends TimeMvpActivity<DetailPresenter> implements DetailContract.View {
    private FrameLayout mFlLoading;
    private TitleBar mTbTitle;
    private ImageView mIvBackground;
    private ImageView mIvCover;
    private TextView mTvName;
    private TextView mTvNameEn;
    private TextView mTvMins;
    private TextView mTvDate;
    private TextView mTvType;
    private TextView mTvScore;
    private TextView mTvPersonCount;
    private TextView mTvStory;
    private TextView mTvTodayTitle;
    private TextView mTvTodayNum;
    private TextView mTvAllTitle;
    private TextView mTvAllNum;
    private RecyclerView mRvActors;
    private RecyclerView mRvImgs;
    // 其他变量
    private Long mCurrentId;
    public static final String MOVIE_ID = "MOVIE_ID";
    private ActorAdapter mActorAdapter;
    private StageImgAdapter mStageAdapter;

    public static void actionStart(Context context, Long id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(MOVIE_ID, id);
        context.startActivity(intent);
    }

    private void initViews() {
        mFlLoading = findViewById(R.id.detail_fl_loading);
        mTbTitle = findViewById(R.id.detail_tb_title);
        mIvBackground = findViewById(R.id.detail_iv_background);
        mIvBackground.setColorFilter(Color.parseColor("#88000000"));
        mIvCover = findViewById(R.id.detail_iv_cover);
        mTvName = findViewById(R.id.detail_tv_name);
        mTvNameEn = findViewById(R.id.detail_tv_name_en);
        mTvMins = findViewById(R.id.detail_tv_mins);
        mTvDate = findViewById(R.id.detail_tv_date);
        mTvType = findViewById(R.id.detail_tv_type);
        mTvScore = findViewById(R.id.detail_tv_score);
        mTvPersonCount = findViewById(R.id.detail_tv_person_count);
        mTvStory = findViewById(R.id.detail_tv_story);
        mTvTodayTitle = findViewById(R.id.detail_tv_today_title);
        mTvTodayNum = findViewById(R.id.detail_tv_today_num);
        mTvAllTitle = findViewById(R.id.detail_tv_all_title);
        mTvAllNum = findViewById(R.id.detail_tv_all_num);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mRvActors = findViewById(R.id.detail_rv_actors);
        mRvActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mActorAdapter = new ActorAdapter(new ArrayList<>(), R.layout.item_actor, false, false);
        mRvActors.setAdapter(mActorAdapter);

        mRvImgs = findViewById(R.id.detail_rv_imgs);
        mRvImgs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mStageAdapter = new StageImgAdapter(new ArrayList<>(), R.layout.item_stageimg, false, false);
        mRvImgs.setAdapter(mStageAdapter);

        mTbTitle.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    @Override
    protected void init(DetailPresenter presenter, Bundle savedInstanceState) {
        initViews();
        mCurrentId = getIntent().getLongExtra(MOVIE_ID, -1);
        long locationId = LocationManager.getInstance().getLocation().getId();
        presenter.fetchMovieDetail(locationId, mCurrentId);
    }

    @Override
    protected DetailPresenter onCreatePresenter() {
        return new DetailPresenter(this, this, new RemoteDetailRepo(), new LocalDetailRepo());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_detail;
    }


    @Override
    public void onLoadDetail(MovieDetail detail) {
        showMovieDetail(detail);
        mFlLoading.setVisibility(View.GONE);
    }

    @Override
    public void onLoadError() {
        showToast("网络出现错误，请检查网络设置");
        finish();
    }

    private void showMovieDetail(MovieDetail detail) {
        mTbTitle.setTitle(detail.getInfo().getName());

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.base_img_loading)
                .error(R.drawable.base_img_loading)
                .dontAnimate()
                .priority(Priority.HIGH);

        Glide.with(this)
                .asBitmap()
                .load(detail.getInfo().getImg())
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        mIvCover.setImageBitmap(resource);
                        mIvBackground.setImageBitmap(
                                EasyBlur.with(DetailActivity.this)
                                        .bitmap(resource)
                                        .radius(25)
                                        .scale(4)
                                        .policy(EasyBlur.BlurPolicy.FAST_BLUR)
                                        .blur());
                    }
                });

        mTvName.setText(detail.getInfo().getName());
        mTvNameEn.setText("英文名：" + detail.getInfo().getNameEn());
        mTvMins.setText("片长：" + detail.getInfo().getMins());
        try {
            DateFormat oriFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = oriFormat.parse(detail.getInfo().getReleaseDate());
            DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            mTvDate.setText("上映时间：" + newFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> typeList = detail.getInfo().getType();
        StringBuffer typeStr = new StringBuffer();
        for (int i = 0; i < typeList.size(); i++) {
            String type = typeList.get(i);
            typeStr.append(type);
            if (i != typeList.size() - 1) {
                typeStr.append("/");
            }
        }
        mTvType.setText("类型：" + typeStr.toString());
        String scoreStr = detail.getInfo().getScore() > 0 ? String.valueOf(detail.getInfo().getScore()) : "0.0";
        mTvScore.setText(scoreStr);
        mTvPersonCount.setText(detail.getInfo().getPersonCount() + "人");
        mTvStory.setText(detail.getInfo().getStory());

        String todayTitle = detail.getBoxOffice().getTodayBoxDesUnit();
        if (!todayTitle.isEmpty()) {
            mTvTodayTitle.setText(detail.getBoxOffice().getTodayBoxDesUnit());
            mTvTodayNum.setText(detail.getBoxOffice().getTodayBoxDes());
        } else {
            mTvTodayTitle.setText("今日实时");
            mTvTodayNum.setText("暂无");
        }

        String totalTitle = detail.getBoxOffice().getTotalBoxUnit();
        if (!totalTitle.isEmpty()) {
            mTvAllTitle.setText(detail.getBoxOffice().getTotalBoxUnit());
            mTvAllNum.setText(detail.getBoxOffice().getTotalBoxDes());
        } else {
            mTvTodayTitle.setText("累计票房");
            mTvTodayNum.setText("暂无");
        }

        mActorAdapter.setDatas(detail.getInfo().getActors());
        mStageAdapter.setDatas(detail.getInfo().getStageImg().getList());
    }

}
