package com.sk.sqhk.module.select.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sk.sqhk.Constant;
import com.sk.sqhk.R;
import com.sk.sqhk.base.BaseActivity;
import com.sk.sqhk.module.home.network.response.SelectXinYongCardObj;
import com.sk.sqhk.module.select.adapter.ZhangDanFragmentAdapter;
import com.sk.sqhk.module.select.fragment.ZhangDanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class WoDeZhangDanActivity extends BaseActivity {

    @BindView(R.id.tl_zhangdan)
    TabLayout tl_zhangdan;

    @BindView(R.id.vp_zhangdan)
    ViewPager vp_zhangdan;

    ZhangDanFragmentAdapter adapter;
    private SelectXinYongCardObj cardObj;

    @Override
    protected int getContentView() {
        setAppTitle("账单");
        return R.layout.act_wode_zhangdan;
    }

    @Override
    protected void initView() {
        cardObj = (SelectXinYongCardObj) getIntent().getSerializableExtra(Constant.IParam.selectXinYongCard);
        String cardId=cardObj.getId()+"";
        List<ZhangDanFragment>list=new ArrayList<>();
        list.add(ZhangDanFragment.newInstance("0",cardId));
        list.add(ZhangDanFragment.newInstance("1",cardId));
        list.add(ZhangDanFragment.newInstance("2",cardId));
        list.add(ZhangDanFragment.newInstance("3",cardId));
        adapter=new ZhangDanFragmentAdapter(getSupportFragmentManager());
        adapter.setList(list);

        vp_zhangdan.setAdapter(adapter);
        vp_zhangdan.setOffscreenPageLimit(list.size()-1);

        tl_zhangdan.setupWithViewPager(vp_zhangdan);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
