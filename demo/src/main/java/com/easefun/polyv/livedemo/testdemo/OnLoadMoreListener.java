package com.easefun.polyv.livedemo.testdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;
    private int itemCount, lastPosition, lastItemCount;
    public abstract void onLoadMore();
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            itemCount = layoutManager.getItemCount();
            lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        } else {
            //Log.e("OnLoadMoreListener", "The OnLoadMoreListener only support LinearLayoutManager");
            return;
        }
        if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
            lastItemCount = itemCount;
            this.onLoadMore();
        }
    }
}

