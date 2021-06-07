package com.example.customadapter.Order;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.customadapter.Order.Orderconfirm.FragmentOrderConfirm;
import com.example.customadapter.Order.OrderDetail.FragmentOrderDetails;
import com.example.customadapter.R;
import com.google.android.material.tabs.TabLayout;

public class CreateOrderActivity extends AppCompatActivity implements FragmentOrderDetails.ISendIdListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String orderId = "";
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_oder);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_order);
        viewPager = (ViewPager) findViewById(R.id.view_pager_order);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void sendIdOrder(String idOrder) {
        orderId = idOrder;
        setOrderId(idOrder);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_order_confirm, new FragmentOrderConfirm());
        fragmentTransaction.commit();
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}