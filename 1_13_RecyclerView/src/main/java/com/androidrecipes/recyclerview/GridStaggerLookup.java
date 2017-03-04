package com.androidrecipes.recyclerview;

import android.support.v7.widget.GridLayoutManager;

/**
 * <p>自定义“交错排列”效果的查询</p>
 * <li>1、实现不同类型的item“交错排列”的效果</li>
 * <li>2、“GridLayoutManager.SpanSizeLookup”的作用是帮助提供当前 item 占据几个span（跨度：横、纵）</li>
 * <li>3、“Lookup”的意思是，查询，布局管理器查询程序员的意图，然后来做出安排</li>
 *
 */
class GridStaggerLookup extends GridLayoutManager.SpanSizeLookup {

    @Override
    public int getSpanSize(int position) {
        return (position % 3 == 0 ? 2 : 1); /*item 当前的位置能被 3 整除，则占据 2 个span，否则占据 1 个 span*/
    }
}
