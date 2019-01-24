
package com.sky.gank.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.sky.gank.util.LogUtils;

/**
 * @author Sky
 */
public class RatioImageView extends android.support.v7.widget.AppCompatImageView {
    /**
     * 设置图片宽高比例
     */
    private int originalWidth = 1;
    private int originalHeight = 1;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalWidth / (float) originalHeight;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }
//            LogUtils.i(LogUtils.TAG,"setMeasuredDimension"+width+"_"+height);
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
