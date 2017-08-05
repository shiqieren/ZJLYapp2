package com.cimc.zjly.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cimc.zjly.ui.frags.subfrags.MyLinkmanListFragment;

import java.util.List;

/**
 * Created by lyw on 2017/7/28.
 */

public class MyTabLinkmanAdapter extends FragmentStatePagerAdapter {
    public static final String TAB_TAG = "@dream@";
    private List<String> mTitles;
    public MyTabLinkmanAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
     /*   if (position == 1) {
            return new MyUnderLinkmanListFragment();
        }*/
        return new MyLinkmanListFragment(position);

        /*String[] title = mTitles.get(position).split(TAB_TAG);
        fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(title[0]);
*/

    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }


}
