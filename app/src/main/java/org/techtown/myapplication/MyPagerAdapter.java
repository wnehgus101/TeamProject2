package org.techtown.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyPagerAdapter extends FragmentStateAdapter {
    private final Fragment[] fragments;

    public MyPagerAdapter(FragmentActivity fragmentActivity, Fragment... fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

    private final String[] tabTitles = new String[]{"프로필", "경력", "상담후기"};

    public String getTabTitle(int position) {
        return tabTitles[position];
    }
}
