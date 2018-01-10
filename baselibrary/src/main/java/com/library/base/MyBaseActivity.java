package com.library.base;

import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.ClickUtils;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.StatusBarUtil;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.BaseDividerListItem;
import com.github.baseclass.activity.IBaseActivity;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.rx.IOCallBack;
import com.library.BuildConfig;
import com.library.R;
import com.library.base.tools.CleanMessageUtil;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Subscriber;


/**
 * Created by Administrator on 2017/6/1.
 */

public abstract class MyBaseActivity extends IBaseActivity implements ProgressLayout.OnAgainInter, View.OnClickListener, LoadMoreAdapter.OnLoadMoreListener {

    /*************************************************/
    protected NestedScrollView nsv;
    protected Toolbar toolbar;
    private boolean showNavigationIcon = true;
    private int navigationIcon = -1;
    protected int pageNum = 2;
    protected int pageSize = 20;
    private String appTitle, appRightTitle;
    private int appTitleColor, appRightTitleColor;
    private int appRightImg;
    private int titleBackgroud = R.color.app_bar;
//    private int statusBarBackgroud = R.color.app_bar;
    protected TextView app_title, app_right_tv;
    protected ImageView app_right_iv;
    private View status_bar, v_bottom_line;
    private boolean hiddenBottomLine;
    protected PtrClassicFrameLayout pcfl;
    protected boolean isPause;
    protected boolean noSetTheme;
    protected ProgressLayout pl_load;

    /****************************************************/
    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void onViewClick(View v);

    protected void initRxBus() {
    }
    protected void myReStart() {
    }
    protected void getOtherData(){};
    protected void getData(int page, boolean isLoad) {
    }
    protected void setClickListener(){};
    @Override
    protected void onPause() {
        super.onPause();
        isPause =true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isPause){
            isPause =false;
            myReStart();
        }
    }

    protected void hiddenBottomLine() {
        hiddenBottomLine = true;
        if (v_bottom_line != null) {
            v_bottom_line.setVisibility(View.GONE);
        }
    }

    protected void setAppTitle(String title) {
        appTitle = title;
        if (app_title != null) {
            if(BuildConfig.DEBUG){
                app_title.setText(appTitle == null ? "" : appTitle+"(DeBug)");
            }else{
                app_title.setText(appTitle == null ? "" : appTitle);
            }
        }
    }

    public void setAppRightTitle(String appRightTitle) {
        this.appRightTitle = appRightTitle;
        if (app_right_tv != null) {
            app_right_tv.setText(appRightTitle == null ? "" : appRightTitle);
        }
    }

    public void setAppRightImg(int appRightImg) {
        this.appRightImg = appRightImg;
        if (app_right_iv != null && appRightImg != 0) {
            app_right_iv.setImageResource(appRightImg);

            app_right_tv.setVisibility(View.GONE);
            app_right_iv.setVisibility(View.VISIBLE);
        }
    }

    public void setNoSetTheme(boolean noSetTheme) {
        this.noSetTheme = noSetTheme;
    }

    public void setTitleBackgroud(@ColorRes int titleBackgroud) {
        this.titleBackgroud = titleBackgroud;
    }

 /*   public void setStatusBarBackgroud(@ColorRes int statusBarBackgroud) {
        this.statusBarBackgroud = statusBarBackgroud;
        if (status_bar != null) {
            status_bar.setBackgroundColor(ContextCompat.getColor(mContext, statusBarBackgroud));
        }
    }*/

    public void setAppTitleColor(@ColorRes  int appTitleColor) {
        this.appTitleColor = appTitleColor;
    }

    public void setAppRightTitleColor(@ColorRes  int appRightTitleColor) {
        this.appRightTitleColor = appRightTitleColor;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mContext = this;
        if (getContentView() != 0) {
            setContentView(getContentView());
            StatusBarUtil.setColor(this,ContextCompat.getColor(mContext,R.color.app_status),0);
//            View rootView = ((ViewGroup) this.findViewById(android.R.id.content))
//                    .getChildAt(0);
//            int navigationBarHeight = PhoneUtils.getNavigationBarHeight(mContext);
//            if(navigationBarHeight>0){
//                rootView.setPadding(0,0,0,navigationBarHeight);
//            }
        }
        if(!noSetTheme){
            setTheme(R.style.AppTheme_NoActionBar);
        }

/*        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }*/

        ButterKnife.bind(this);
        /*if (null != findViewById(R.id.status_bar)) {
            status_bar = findViewById(R.id.status_bar);
            int statusBarHeight = getStatusBarHeight(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.height = statusBarHeight;
            status_bar.setLayoutParams(layoutParams);
            status_bar.setBackgroundColor(ContextCompat.getColor(mContext, statusBarBackgroud));
        }*/
        if (null != findViewById(R.id.v_bottom_line)) {
            v_bottom_line = findViewById(R.id.v_bottom_line);
            if (hiddenBottomLine) {
                v_bottom_line.setVisibility(View.GONE);
            }
        }
        if (null != findViewById(R.id.toolbar)) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }
            });
            toolbar.setBackgroundColor(ContextCompat.getColor(mContext, titleBackgroud));
        }
        if (null != findViewById(R.id.app_title)) {
            app_title = (TextView) findViewById(R.id.app_title);
            setAppTitle(appTitle);
            if(null!=findViewById(R.id.v_bottom_line)){
                if(TextUtils.isEmpty(appTitle)||hiddenBottomLine){
                    findViewById(R.id.v_bottom_line).setVisibility(View.GONE);
                }
            }

            if (appTitleColor != 0) {
                app_title.setTextColor(ContextCompat.getColor(mContext,appTitleColor));
            }
        }
        if (null != findViewById(R.id.app_right_tv)) {
            app_right_tv = (TextView) findViewById(R.id.app_right_tv);
        }
        if (null != findViewById(R.id.app_right_iv)) {
            app_right_iv = (ImageView) findViewById(R.id.app_right_iv);
        }
        if (appRightImg != 0) {
            app_right_iv.setImageResource(appRightImg);

            app_right_tv.setVisibility(View.GONE);
            app_right_iv.setVisibility(View.VISIBLE);
        }
        if (appRightTitle != null) {
            app_right_tv.setText(appRightTitle);
            app_right_tv.setVisibility(View.VISIBLE);
            app_right_iv.setVisibility(View.GONE);
            if (appRightTitleColor != 0) {
                app_right_tv.setTextColor(ContextCompat.getColor(mContext,appRightTitleColor));
            }
        }
        if (null != findViewById(R.id.pcfl_refresh)) {
            pcfl = (PtrClassicFrameLayout) findViewById(R.id.pcfl_refresh);
            pcfl.setLastUpdateTimeRelateObject(this);
            pcfl.disableWhenHorizontalMove(true);
            pcfl.setHorizontalMoveFlag(3);
            pcfl.setOffsetXFlag(3);
            pcfl.setScaledTouchSlopFlag((float) 0.5);
            pcfl.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    getOtherData();
                    getData(1, false);
                }
            });
        }
        if (null != findViewById(R.id.pl_load)) {
            pl_load = (ProgressLayout) findViewById(R.id.pl_load);
            pl_load.setInter(this);
        }
        if (null != findViewById(R.id.nsv)) {
            nsv = (NestedScrollView) findViewById(R.id.nsv);
        }
//        setInput();
        initRxBus();
        initView();
        setClickListener();
        if (toolbar != null) {
            if (navigationIcon != -1) {
                getSupportActionBar().setHomeAsUpIndicator(navigationIcon);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back);
//                getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_back_black);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(showNavigationIcon);
        }
        initData();
    }

    protected void setBackIcon(int resId) {
        navigationIcon = resId;
    }

    protected void hiddenBackIcon() {
        showNavigationIcon = false;
    }

    protected void hiddenBackIcon(boolean show) {
        showNavigationIcon = show;
    }

    protected String getSStr(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        } else if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        } else {
            return null;
        }
    }

    public void showProgress() {
        if (pl_load != null) {
            pl_load.showProgress();
        }
    }

    public void showContent() {
        if (pl_load != null) {
            pl_load.showContent();
        }
    }

    public void showErrorText() {
        if (pl_load != null) {
            pl_load.showErrorText();
        }
    }

    @Override
    public void onClick(View v) {
        if (!ClickUtils.isFastClick(v, 800)) {
            onViewClick(v);
        }
    }
    private void setInput() {
        /*if(mContext instanceof ShangJiaActivity){
            return;
        }*/
        final View rootView = ((ViewGroup) this.findViewById(android.R.id.content))
                .getChildAt(0);
        if(null==rootView){
            Log.i("MyBaseActivity","rootView=null");
            return;
        }
        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                Rect rect= new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = decorView.getRootView().getHeight();
                int heightDifferent = screenHeight - rect.bottom- PhoneUtils.getNavigationBarHeight(mContext);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rootView.getLayoutParams();
                lp.setMargins(0, 0, 0, heightDifferent);
                rootView.requestLayout();
            }
        });
    }

    @Override
    public void loadMore() {
        getData(pageNum, true);
    }

    @Override
    public void again() {
        initData();
    }


    protected boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    protected boolean notEmpty(List list) {
        return !(list == null || list.size() == 0);
    }

    protected String getRnd() {
        Random random = new Random();
        int rnd = random.nextInt(9000) + 1000;
        return rnd + "";
    }

    protected BaseDividerListItem getItemDivider() {
        return new BaseDividerListItem(mContext,2, R.color.background_f2);
    }

    protected BaseDividerListItem getItemDivider(int height) {
        return new BaseDividerListItem(mContext,height, R.color.background_f2);
    }

    protected BaseDividerListItem getItemDivider(int height, int color) {
        return new BaseDividerListItem(mContext, height, color);
    }



    public void deleteCache() {
        deleteCache(null);
    }
    public void deleteCache(final TextView textView) {
        RXStart(new IOCallBack<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                CleanMessageUtil.clearAllCache(getApplicationContext());
                try {
                    String totalCacheSize = CleanMessageUtil.getTotalCacheSize(getApplicationContext());
                    subscriber.onNext(totalCacheSize);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onMyNext(String totalCacheSize) {
                showMsg("清除成功");
                if(textView!=null){
                    textView.setText(totalCacheSize);
                }
            }
        });

    }


}
