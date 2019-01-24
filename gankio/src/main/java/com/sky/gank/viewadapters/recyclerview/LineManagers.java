package com.sky.gank.viewadapters.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * 类名称：
 * 类功能：
 * 类作者：Administrator
 * 类日期：2019/1/8 0008.
 **/
public class LineManagers {

    protected LineManagers() {
    }

    public interface LineManagerFactory {
        RecyclerView.ItemDecoration create(RecyclerView recyclerView);
    }


    public static LineManagerFactory both() {
        return new LineManagerFactory() {
            @Override
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.BOTH);
            }
        };
    }

    public static LineManagerFactory horizontal() {
        return new LineManagerFactory() {
            @Override
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.HORIZONTAL);
            }
        };
    }

    public static LineManagerFactory vertical() {
        return new LineManagerFactory() {
            @Override
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerLine(recyclerView.getContext(), DividerLine.LineDrawMode.VERTICAL);
            }
        };
    }
}
