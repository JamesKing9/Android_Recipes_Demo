package com.androidrecipes.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>自定义条目的装饰：RecyclerView.ItemDecoration</p>
 * <li>1、自己绘制条目的装饰</li>
 */
class ConnectorDecoration extends RecyclerView.ItemDecoration {

    /**画笔*/
    private Paint mLinePaint;
    /**画出的线段的长度*/
    private int mLineLength;

    ConnectorDecoration(Context context) {
        super();
        mLineLength = context.getResources()
                .getDimensionPixelOffset(R.dimen.inset_margin);
        int connectorStroke = context.getResources()
                .getDimensionPixelSize(R.dimen.connector_stroke);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG); /*反锯齿，antialiasing*/
        mLinePaint.setColor(Color.GREEN);
        mLinePaint.setStyle(Paint.Style.STROKE); /*钢笔模式*/
        mLinePaint.setStrokeWidth(connectorStroke); /*设置笔头宽度*/
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        /**获得RecyclerView 的布局管理器*/
        final RecyclerView.LayoutManager manager = parent.getLayoutManager();
        for (int i=0; i < parent.getChildCount(); i++) { /*遍历RecyclerView 中孩子条目*/
            final View child = parent.getChildAt(i); /*获得对应位置的孩子条目view*/
            boolean isLarge = parent.getChildViewHolder(child) /*获得孩子条目view 的viewholder*/
                    .getPosition() % 3 == 0; /*判断是否能被 3 整除*/

            if (!isLarge) { /*如果不能被 3 整除，则执行下面的逻辑*/
                /*获取孩子view 的装饰物的左边缘的 offsets*/
                final int childLeft = manager.getDecoratedLeft(child);
                final int childRight = manager.getDecoratedRight(child);
                final int childTop = manager.getDecoratedTop(child);
                final int x = childLeft + ((childRight - childLeft) / 2); /*条目横向的中点*/

                /*Draw a line segment（线段） with the specified start and stop x,y coordinates（坐标）,
                using the specified paint（画笔）.*/
                c.drawLine(
                        x, childTop - mLineLength, /*装饰物的起点的坐标*/
                        x, childTop + mLineLength, /*装饰物的终点的坐标*/
                        mLinePaint);
            }
        }
    }
}
