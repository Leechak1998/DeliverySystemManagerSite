package com.example.deliverysystemmanagersite.adapter;

import android.content.Context;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.controller.fragment.DriverFragment;
import com.example.deliverysystemmanagersite.controller.fragment.PackageFragment;
import com.example.deliverysystemmanagersite.controller.fragment.SiteFragment;
import com.example.deliverysystemmanagersite.controller.fragment.VendorFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public PageAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("-------" + position + "---------");
        Fragment fragment;
        switch (position) {
            case 0:
                System.out.println("返回第一个");
                fragment = PackageFragment.newInstance();
                break;
            case 1:
                System.out.println("返回第二个");
                fragment = DriverFragment.newInstance();
                break;
            case 2:
                System.out.println("返回第三个");
                fragment = VendorFragment.newInstance();
                break;
            case 3:
                System.out.println("返回第四个");
                fragment = SiteFragment.newInstance();
                break;
            default:
                System.out.println("返回null");
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

}
