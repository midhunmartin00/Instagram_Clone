package com.example.carddemo.ui.notifications;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    int numTabs;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numTabs=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return firstFragment.newInstance();
            case 1:
                return secondFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
