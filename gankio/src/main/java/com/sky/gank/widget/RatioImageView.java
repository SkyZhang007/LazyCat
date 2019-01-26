
package com.sky.gank.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.sky.gank.R;
import com.sky.gank.util.LogUtils;

/**
 * @author Sky
 */
public class RatioImageView extends android.support.v7.widget.AppCompatImageView {
    /**
     * 设置图片宽高比例
     */
    private float originalWidth = 1;
    private float originalHeight = 1;

    public RatioImageView(Context context) {
        this(context,null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.RatioImageView,defStyleAttr,0);
        originalWidth = a.getFloat(R.styleable.RatioImageView_scaleWidth,1f);
        originalHeight = a.getFloat(R.styleable.RatioImageView_scaleHeight,1f);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = originalWidth / originalHeight;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
