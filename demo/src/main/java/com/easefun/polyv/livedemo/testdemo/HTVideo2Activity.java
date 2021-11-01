package com.easefun.polyv.livedemo.testdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.easefun.polyvsdk.R;
import com.easefun.polyvsdk.abtetetete.pagerlayoutmanager.OnViewPagerListener;
import com.easefun.polyvsdk.abtetetete.pagerlayoutmanager.ViewPagerLayoutManager;

public class HTVideo2Activity extends AppCompatActivity implements OnViewPagerListener {
    RecyclerView recyclerView;
    ViewPagerLayoutManager pagerLayoutManager;
    com.easefun.polyvsdk.abtetetete.HTVideo2Adapter videoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht_video);
        initViews();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                final int action = e.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN://手指按下
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_MOVE://手指移动（从手指按下到抬起 move多次执行）
                        break;
                    case MotionEvent.ACTION_UP://手指抬起
                        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING &&
                                pagerLayoutManager.findSnapPosition() == 0) {
                            if (recyclerView.getChildAt(0).getY() == 0 &&
                                    recyclerView.canScrollVertically(1)) {//下滑操作
                                recyclerView.stopScroll();
                            }
                        }
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

        pagerLayoutManager = new ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL);
        pagerLayoutManager.setOnViewPagerListener(this);
        videoAdapter = new com.easefun.polyvsdk.abtetetete.HTVideo2Adapter(this, recyclerView);

        recyclerView.setLayoutManager(pagerLayoutManager);
        recyclerView.setAdapter(videoAdapter);

        videoAdapter.addData(com.easefun.polyvsdk.abtetetete.MockData.getMockVideoData());
    }

    @Override
    public void onInitComplete() {
        playVideo(0);
    }

    @Override
    public void onPageSelected(int position, boolean isBottom) {
        playVideo(position);
        if(isBottom) {
            videoAdapter.addData(com.easefun.polyvsdk.abtetetete.MockData.getMockVideoData());
            //videoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageRelease(boolean isNext, int position) {
        releaseVideo(position);
    }

    private void playVideo(int position) {
        final com.easefun.polyvsdk.abtetetete.HTVideo2Adapter.VideoViewHolder viewHolder = (com.easefun.polyvsdk.abtetetete.HTVideo2Adapter.VideoViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        VideoEntity videoEntity = videoAdapter.getDataByPosition(position);
        if (viewHolder != null && !viewHolder.videoView.isPlaying()) {

            viewHolder.videoView.setVid(videoEntity.getVideoUrl());
//            viewHolder.videoView.getMediaPlayer().setOnInfoListener(new IMediaPlayer.OnInfoListener() {
//                @Override
//                public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {
//                    if (what == IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
//                        viewHolder.sdvCover.setVisibility(View.INVISIBLE);
//                    }
//                    return false;
//                }
//            });
//            viewHolder.videoView.setOnVideoProgressUpdateListener(new ListVideoView.OnVideoProgressListener() {
//                @Override
//                public void onProgress(float progress, long currentTime) {
//                    Log.d("youzai", "progresss---->" + progress + "\t" + "currentTime---->" + currentTime);
//                }
//            });
            viewHolder.videoView.setLooping(true);
//            viewHolder.videoView.prepareAsync();
            viewHolder.videoView.start();


        }
    }


    private void releaseVideo(int position) {
        com.easefun.polyvsdk.abtetetete.HTVideo2Adapter.VideoViewHolder viewHolder = (com.easefun.polyvsdk.abtetetete.HTVideo2Adapter.VideoViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        if (viewHolder != null) {
            viewHolder.videoView.release();
        }
    }
}
