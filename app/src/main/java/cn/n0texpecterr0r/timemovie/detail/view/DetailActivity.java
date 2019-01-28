package cn.n0texpecterr0r.timemovie.detail.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.hjq.bar.TitleBar;

import cn.n0texpecterr0r.timemovie.R;

public class DetailActivity extends AppCompatActivity {
    private TitleBar mTbTitle;
    private LinearLayout mLlBackground;
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

    public static void actionStart(Context context, Long id){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(MOVIE_ID, id);
        context.startActivity(intent);
    }

    private void initViews() {
        mTbTitle = findViewById(R.id.detail_tb_title);
        mLlBackground = findViewById(R.id.detail_ll_background);
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
        mRvActors = findViewById(R.id.detail_rv_actors);
        mRvImgs = findViewById(R.id.detail_rv_imgs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        mCurrentId = getIntent().getLongExtra(MOVIE_ID, -1);


    }


}
