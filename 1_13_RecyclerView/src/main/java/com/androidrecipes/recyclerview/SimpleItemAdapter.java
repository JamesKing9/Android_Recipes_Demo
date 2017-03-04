package com.androidrecipes.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ItemHolder> {

    /*
     * Click handler interface(点击事件处理接口). RecyclerView does not have
     * its own built in like AdapterViews do.
     */
    interface OnItemClickListener {
        void onItemClick(ItemHolder item, int position);
        /*redundant，多余的*/
    }

    private static final String[] ITEMS = {
            "Apples", "Oranges", "Bananas", "Mangos",
            "Carrots", "Peas", "Broccoli",
            "Pork", "Chicken", "Beef", "Lamb"
    };

    /**item 的集合*/
    private List<String> mItems;

    private OnItemClickListener mOnItemClickListener;
    private LayoutInflater mLayoutInflater;

    SimpleItemAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        //Create static list of dummy items
        mItems = new ArrayList<String>();
        mItems.addAll(Arrays.asList(ITEMS));
        mItems.addAll(Arrays.asList(ITEMS));
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.collection_item, parent, false);

        return new ItemHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setTitle("Item #"+(position+1));
        holder.setSummary(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /* Methods to manage modifying the data set */

    /**
     * 在指定的 position 处添加 item
     * @param item
     * @param position
     */
    public void insertItemAtIndex(String item, int position) {
        mItems.add(position, item);
        //Notify the view to trigger（引发） a change animation
        notifyItemInserted(position);
    }

    public void removeItemAtIndex(int position) {
        if (position >= mItems.size()) return; /*保证RecyclerView 容器中至少有一个 item*/

        mItems.remove(position);
        //Notify the view to trigger a change animation
        notifyItemRemoved(position);
    }

    /* Required implementation of ViewHolder to wrap item view */
    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SimpleItemAdapter mParent;
        private TextView mTitleView, mSummaryView;

        public ItemHolder(View itemView, SimpleItemAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            mParent = parent;

            mTitleView = (TextView) itemView.findViewById(R.id.text_title);
            mSummaryView = (TextView) itemView.findViewById(R.id.text_summary);
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }

        /**设置摘要*/
        public void setSummary(CharSequence summary) {
            mSummaryView.setText(summary);
            /*summary，摘要*/
        }

        public CharSequence getSummary() {
            return mSummaryView.getText();
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = mParent.getOnItemClickListener();
            if (listener != null) {
                listener.onItemClick(this, getPosition());
            }
        }
    }
}
