package com.yahyam.flagsandcapitals;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new HomeFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new TheardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}