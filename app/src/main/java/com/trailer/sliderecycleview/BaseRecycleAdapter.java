package com.trailer.sliderecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EchoZhou on 2016/10/14.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter {

    protected Context mContext;
    protected List<T> mList = new ArrayList<T>();

    protected static final int TYPE_FOOTER = 1;
    protected static final int TYPE_ITEM = 0;

    protected int mLastPosition = -1;

    protected boolean mIsShowFooter = false;

    public BaseRecycleAdapter(Context context, List<T> mList) {
        this.mContext = context;
        if (mList != null) {
            this.mList = mList;
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.size() == 0) {
            return 0;
        }
        int itemSize = mList.size();
        if (mIsShowFooter) {
            itemSize += 1;
        }
        return itemSize;
    }

    public void updateListView(List list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void showFooter() {
        mIsShowFooter = true;
        notifyItemInserted(getItemCount());
    }

    public void hideFooter() {
        mIsShowFooter = false;
        notifyItemRemoved(getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsShowFooter && isFooterPosition(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    protected boolean isFooterPosition(int position) {
        return (getItemCount() - 1) == position;
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, int type) {
        if (position > mLastPosition/* && !isFooterPosition(position)*/) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), type);
            holder.itemView.startAnimation(animation);
            mLastPosition = position;
        }
    }

    public interface ItemClickListener {
        void onClick(int position);
    }

}

