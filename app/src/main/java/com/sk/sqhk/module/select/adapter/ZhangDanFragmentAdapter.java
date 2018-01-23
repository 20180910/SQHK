package com.sk.sqhk.module.select.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sk.sqhk.module.select.fragment.ZhangDanFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ZhangDanFragmentAdapter extends FragmentStatePagerAdapter {
    String[] title=new String[]{"待执行","成功","中断","失败"};
    List<ZhangDanFragment> list;
    public ZhangDanFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setList(List<ZhangDanFragment> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
