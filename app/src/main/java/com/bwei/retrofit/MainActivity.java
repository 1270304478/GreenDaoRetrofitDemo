package com.bwei.retrofit;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwei.retrofit.adapter.ViewPagerAdapter;
import com.bwei.retrofit.fragment.Fragment01;
import com.bwei.retrofit.fragment.Fragment02;
import com.bwei.retrofit.fragment.Fragment03;
import com.bwei.retrofit.fragment.Fragment04;
import com.bwei.retrofit.fragment.Fragment05;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    ViewPagerAdapter adapter;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rg)
    RadioGroup rg;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList();
        list.add(new Fragment01());
        list.add(new Fragment02());
        list.add(new Fragment03());
        list.add(new Fragment04());
        list.add(new Fragment05());
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb1:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb4:
                        viewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb5:
                        viewPager.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    Toast.makeText(context,"当前所连接的网络可用",Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        Toast.makeText(context,"网络不可用",Toast.LENGTH_SHORT).show();
        return false;
    }


}