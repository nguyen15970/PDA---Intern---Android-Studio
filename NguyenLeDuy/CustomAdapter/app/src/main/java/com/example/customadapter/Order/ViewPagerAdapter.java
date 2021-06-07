package com.example.customadapter.Order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.customadapter.Order.Orderconfirm.FragmentOrderConfirm;
import com.example.customadapter.Order.OrderDetail.FragmentOrderDetails;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch  (position){
            case 1:
                return new FragmentOrderConfirm();
            default: return  new FragmentOrderDetails()  ;
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title= "Chi tiết";
                break;
            case 1:
                title= "Xác nhận";
                break;
        }
        return title;
    }
}
