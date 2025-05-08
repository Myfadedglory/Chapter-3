package com.example.chapter3.homework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ch3Ex3Activity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private List<String> tabTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        initData();
        initViewPager();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        tabTitleList = new ArrayList<>();

        int count = 20; // 这里设置你想要传递的 count 值

        // 添加两个固定的 Fragment
        fragmentList.add(PlaceholderFragment.newInstance(count));
        fragmentList.add(PlaceholderFragment.newInstance(count));

        // 添加两个固定的标签
        tabTitleList.add("好友列表" + "(" + count + ")");
        tabTitleList.add("我的好友");
    }

    private void initViewPager() {
        viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return fragmentList.size();
            }
        });

        // 将 TabLayout 和 ViewPager2 关联起来
        for (int i = 0; i < tabTitleList.size(); i++) {
            Objects.requireNonNull(tabLayout.getTabAt(i)).setText(tabTitleList.get(i));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}