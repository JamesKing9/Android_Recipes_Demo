package com.androidrecipes.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>自定义条目的装饰：RecyclerView.ItemDecoration</p>
 * <li>1、"插入间距"效果的装饰</li>
 */
public class InsetDecoration extends RecyclerView.ItemDecoration {

    private int mInsetMargin;

    public InsetDecoration(Context context) {
        super();
        mInsetMargin = context.getResources()
                .getDimensionPixelOffset(R.dimen.inset_margin);
    }

    /*Rect表示：Rect holds four integer coordinates for a rectangle.*/
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mInsetMargin, mInsetMargin, mInsetMargin, mInsetMargin); /*设置上下左右的margin值*/
    }
}
