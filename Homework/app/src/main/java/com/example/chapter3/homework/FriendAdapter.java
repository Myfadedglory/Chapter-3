package com.example.chapter3.homework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private Context mContext;
    private List<String> mFriendList;
    private int selectedPosition = -1;

    public FriendAdapter(Context context, List<String> friendList) {
        mContext = context;
        mFriendList = friendList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String friendName = mFriendList.get(position);
        holder.tvFriendName.setText(friendName);

        holder.itemLayout.setSelected(position == selectedPosition);

        holder.itemLayout.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                notifyItemChanged(selectedPosition);
            }
            selectedPosition = position;
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView tvFriendName;
        LinearLayout itemLayout;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFriendName = itemView.findViewById(R.id.tv_friend_name);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}