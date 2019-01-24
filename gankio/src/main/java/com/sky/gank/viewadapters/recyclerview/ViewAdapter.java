package com.sky.gank.viewadapters.recyclerview;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sky.gank.command.BindingCommand;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2019/1/8 0008.
 **/
public class ViewAdapter {

    @BindingAdapter("lineManager")
    public static void setLineManager(RecyclerView recyclerView, LineManagers.LineManagerFactory lineManagerFactory) {
        recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView));
    }

    @BindingAdapter(value = {"onScrollChangeCommand", "onScrollStateChangedCommand"}, requireAll = false)
    public static void onScrollChangeCommand(final RecyclerView recyclerView,
                                             final BindingCommand<ScrollDataWrapper> onScrollChangeCommand,
                                             final BindingCommand<Integer> onScrollStateChangedCommand) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onScrollChangeCommand != null) {
                    onScrollChangeCommand.execute(new ScrollDataWrapper(dx, dy, state));
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state = newState;
                if (onScrollStateChangedCommand != null) {
                    onScrollStateChangedCommand.execute(newState);
                }
            }
        });

    }

    @BindingAdapter({"onLoadMoreCommand"})
    public static void onLoadMoreCommand(final RecyclerView recyclerView, final BindingCommand<Integer> onLoadMoreCommand) {
        RecyclerView.OnScrollListener listener = new OnScrollListener(onLoadMoreCommand);
        recyclerView.addOnScrollListener(listener);
    }

    public static class OnScrollListener extends RecyclerView.OnScrollListener {

        /**
         * 可以替换为最后完全可见 View
         * findLastCompletelyVisibleItemPosition
         */
        // 最后几个可见项的位置（用于 StaggeredGridLayoutManager 瀑布流）
        private int[] lastVisiblePositions;
        // 最后一个可见项的位置
        private int lastVisibleItemPosition;
        private PublishSubject<Integer> methodInvoke = PublishSubject.create();
        private BindingCommand<Integer> onLoadMoreCommand;

        public OnScrollListener(final BindingCommand<Integer> onLoadMoreCommand) {
            this.onLoadMoreCommand = onLoadMoreCommand;
            methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            onLoadMoreCommand.execute(integer);
                        }
                    });
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            // 找到最后一个完全可见项的位置
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
                if (lastVisiblePositions == null) {
                    lastVisiblePositions = new int[manager.getSpanCount()];
                }
                manager.findLastVisibleItemPositions(lastVisiblePositions);
                lastVisibleItemPosition = getMaxPosition(lastVisiblePositions);
            } else if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else {
                throw new RuntimeException("Unsupported LayoutManager.");
            }
        }

        private int getMaxPosition(int[] positions) {
            int max = positions[0];
            for (int i = 1; i < positions.length; i++) {
                if (positions[i] > max) {
                    max = positions[i];
                }
            }
            return max;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            // 通过比对 最后完全可见项位置 和 总条目数，来判断是否滑动到底部
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (visibleItemCount > 0 && lastVisibleItemPosition >= totalItemCount - 1) {
                    onScrollToBottom(recyclerView);
                }
            }
        }

        /**
         * 可独立为接口
         */
        public void onScrollToBottom(RecyclerView recyclerView) {
            if (onLoadMoreCommand != null) {
                methodInvoke.onNext(recyclerView.getAdapter().getItemCount());
            }
        }
    }


    public static class ScrollDataWrapper {
        public float scrollX;
        public float scrollY;
        public int state;

        public ScrollDataWrapper(float scrollX, float scrollY, int state) {
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.state = state;
        }
    }
}
