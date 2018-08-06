package com.nexters.moodumdum.adpater;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nexters.moodumdum.FragmentMyJomun;
import com.nexters.moodumdum.FragmentMyWrite;

/**
 * Created by User on 2018-01-23.
 */

public class MyPageTabAdapter extends FragmentStatePagerAdapter{
    private final static int tabCount = 2;

    private Context mContext;

    public MyPageTabAdapter(FragmentManager fm) {
        super( fm );
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentMyWrite fragmentMyWrite= new FragmentMyWrite();
                return fragmentMyWrite;
            case 1:
                FragmentMyJomun fragmentMyJomun = new FragmentMyJomun();
                return fragmentMyJomun;
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "내가 쓴 글";
            case 1:
                return "내가 조문한 글";
            default:
                return null;
        }
    }

}
