package com.library.base;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.androidtools.ClickUtils;
import com.github.baseclass.BaseDividerListItem;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.fragment.IBaseFragment;
import com.github.baseclass.rx.RxBus;
import com.library.R;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/7/13.
 */

public abstract class MyBaseFragment extends IBaseFragment implements View.OnClickListener,ProgressLayout.OnAgainInter,LoadMoreAdapter.OnLoadMoreListener{
    protected int pageNum=2;
    protected int pageSize=20;
    protected NestedScrollView nsv;
    private boolean isFirstLoadData=true;
    private boolean isPrepared;
    protected PtrClassicFrameLayout pcfl;
    /************************************************/
    protected abstract int getContentView();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void onViewClick(View v);
    protected void initRxBus(){};
    protected boolean isPause;
    protected void myReStart() {
    }
    protected void getOtherData(){};
    protected void getData(int page,boolean isLoad){};
    protected Unbinder mUnBind;
    protected String TAG=this.getClass().getSimpleName();
    protected ProgressLayout pl_load;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        mUnBind = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(null!=view.findViewById(R.id.pcfl_refresh)){
            pcfl = (PtrClassicFrameLayout) view.findViewById(R.id.pcfl_refresh);
            pcfl.setLastUpdateTimeRelateObject(this);
            pcfl.disableWhenHorizontalMove(true);
            pcfl.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    getOtherData();
                    getData(1,false);
                }
            });
        }
        if(null!=view.findViewById(R.id.pl_load)){
            pl_load = (ProgressLayout) view.findViewById(R.id.pl_load);
            pl_load.setInter(this);
        }
        if(null!=view.findViewById(R.id.nsv)){
            nsv = (NestedScrollView) view.findViewById(R.id.nsv);
        }
        initView();
        initRxBus();
        isPrepared=true;
        setUserVisibleHint(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        isPause =true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            isPause =true;
        }else{
            isPause =false;
            myReStart();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(isPause){
            isPause =false;
            myReStart();
        }
    }

    public void showProgress(){
        if (pl_load != null) {
            pl_load.showProgress();
        }
    }
    public void showContent(){
        if (pl_load != null) {
            pl_load.showContent();
        }
    }
    public void showErrorText(){
        if (pl_load != null) {
            pl_load.showErrorText();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isFirstLoadData&&isPrepared&&getUserVisibleHint()&&isVisibleToUser){
            initData();
            isFirstLoadData=false;
        }
    }
    protected String getSStr(View view){
        if(view instanceof TextView){
            return ((TextView)view).getText().toString();
        } else if (view instanceof EditText) {
            return ((EditText)view).getText().toString();
        }else{
            return null;
        }
    }
    @Override
    public void onClick(View v) {
        if(!ClickUtils.isFastClick(v)){
            onViewClick(v);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        mUnBind.unbind();
        RxBus.getInstance().removeAllStickyEvents();
    }
    @Override
    public void loadMore() {
        getData(pageNum,true);
    }
    @Override
    public void again() {
        initData();
    }

    protected boolean isEmpty(List list){
        return list == null || list.size() == 0;
    }
    protected boolean notEmpty(List list){
        return !(list == null || list.size() == 0);
    }
    protected String getRnd(){
        Random random = new Random();
        int rnd = random.nextInt(9000) + 1000;
        return rnd+"";
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


}
