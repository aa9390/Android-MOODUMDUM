package com.nexters.moodumdum.util;

import android.view.View;

import com.fashare.stack_layout.StackLayout;

/**
 * Created by kimhyehyeon on 2018. 2. 10..
 */

public class MyStackPageTransformer extends StackLayout.PageTransformer {
    private float mMinScale;    // 栈底: 最小页面缩放比
    private float mMaxScale;    // 栈顶: 最大页面缩放比
    private int mStackCount;    // 栈内页面数

    private float mPowBase;     // 基底: 相邻两 page 的大小比例

    /**
     *
     * @param minScale 栈底: 最小页面缩放比
     * @param maxScale 栈顶: 最大页面缩放比
     * @param stackCount 栈内页面数
     */
    public MyStackPageTransformer(float minScale, float maxScale, int stackCount) {
        mMinScale = minScale;
        mMaxScale = maxScale;
        mStackCount = stackCount;

        if(mMaxScale < mMinScale)
            throw new IllegalArgumentException("The Argument: maxScale must bigger than minScale !");
        mPowBase = (float) Math.pow(mMinScale/mMaxScale, 1.0f/mStackCount);
    }

    public MyStackPageTransformer() {
        this(1f, 1f, 2);
    }

    public final void transformPage(View view, float position, boolean isSwipeLeft) {
        View parent = (View) view.getParent();

        int pageWidth = parent.getMeasuredWidth();
        int pageHeight = parent.getMeasuredHeight();

        view.setPivotX(pageWidth/2);
        view.setPivotY(pageHeight);

        float bottomPos = mStackCount-1;

        if (view.isClickable())
            view.setClickable(false);

        if (position == -1) { // [-1]: 完全移出屏幕, 待删除
            // This page is way off-screen to the left.
            view.setVisibility(View.GONE);

        } else if (position < 0) { // (-1,0): 拖动中
            // Use the default slide transition when moving to the left page
            view.setVisibility(View.VISIBLE);

            view.setTranslationX(0);
            view.setScaleX(mMaxScale);
            view.setScaleY(mMaxScale);

        } else if (position <= bottomPos) { // [0, mStackCount-1]: 堆栈中
            int index = (int)position;  // 整数部分
            float minScale = mMaxScale * (float) Math.pow(mPowBase, index+1);
            float maxScale = mMaxScale * (float) Math.pow(mPowBase, index);
            float curScale = mMaxScale * (float) Math.pow(mPowBase, position);

            view.setVisibility(View.VISIBLE);

            // 从上至下, 调整堆叠位置
            view.setTranslationY(- pageHeight * (1-mMaxScale) * (bottomPos-position) / bottomPos);

            // 从上至下, 调整卡片大小
            float scaleFactor = minScale + (maxScale - minScale) * (1 - Math.abs(position - index));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // 只有最上面一张可点击
            if(position == 0){
                if(!view.isClickable())
                    view.setClickable(true);
            }

        } else { // (mStackCount-1, +Infinity]: 待显示(堆栈中展示不下)
            view.setVisibility(View.GONE);
        }
    }
}
