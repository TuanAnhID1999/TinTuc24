package com.example.administrator.miniproject;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyDapter extends FragmentPagerAdapter {
    private String tab[] = {"Tin Tức", "Đã Lưu", "Yêu Thích"};
    private FragmentFirst tabTintuc;
    private FragmenThree tabLuu;
    private FragmentSenCon tabThich;

    public MyDapter(FragmentManager fm) {
        super(fm);
        tabTintuc = new FragmentFirst();
        tabLuu = new FragmenThree();
        tabThich = new FragmentSenCon();
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return tabTintuc;
        } else if (i == 1) {
            return tabLuu;
        } else if (i == 2) {
            return tabThich;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
