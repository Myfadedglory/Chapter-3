package com.example.chapter3.homework;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    public static final String ARG_COUNT = "count";
    public int friendCount;

    private LottieAnimationView loadingAnimation;
    private RecyclerView friendList;

    public static PlaceholderFragment newInstance(int count) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, count);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        loadingAnimation = view.findViewById(R.id.loading_animation);
        friendList = view.findViewById(R.id.friend_list);
        friendList.setAlpha(0f);

        // 获取传递的 count 参数
        if (getArguments() != null) {
            friendCount = getArguments().getInt(ARG_COUNT, 0);
        }

        // 模拟好友数据
        List<String> friends = new ArrayList<>();
        for (int i = 1; i <= friendCount; i++) {
            friends.add("好友 " + i);
        }

        FriendAdapter friendAdapter = new FriendAdapter(requireContext(), friends);
        friendList.setLayoutManager(new LinearLayoutManager(requireContext()));
        friendList.setAdapter(friendAdapter);

        // 模拟 5s 后执行
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // 实现动画，将 lottie 控件淡出，列表数据淡入
                fadeOutLottie();
                fadeInListData();
            }
        }, 5000);

        return view;
    }

    private void fadeOutLottie() {
        loadingAnimation.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingAnimation.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    private void fadeInListData() {
        friendList.animate()
                .alpha(1f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        friendList.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }
}