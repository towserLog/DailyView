package io.paizi.supportview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by pai on 2016/12/21.
 *
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<String> listData;
    private List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> listData) {
        super(fm);
        this.listData = listData;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
            return isEmpty()?0:listData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "第"+position+"页";
    }

    public boolean isEmpty(){
        return listData ==null;
    }
}
