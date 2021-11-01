package com.easefun.polyv.livedemo.testdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easefun.polyvsdk.R;
import com.easefun.polyvsdk.video.PolyvVideoView;

import java.util.ArrayList;
import java.util.List;

public class HTVideo2Adapter extends RecyclerView.Adapter<HTVideo2Adapter.VideoViewHolder> {
    private List<VideoEntity> dataList = new ArrayList<>();

    private Context mContext;

    private RecyclerView recyclerView;

    public HTVideo2Adapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ht_video, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        //videoViewHolder.videoView.setVid(dataList.get(i).getVideoUrl());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<VideoEntity> newDataList) {
        this.dataList.addAll(newDataList);
    }

    public VideoEntity getDataByPosition(int position) {
        return dataList.get(position);
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        PolyvVideoView videoView;
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.polyv_video_view);
            videoView.setOpenPreload(true);
        }
    }
}
