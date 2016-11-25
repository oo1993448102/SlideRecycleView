package com.trailer.sliderecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EchoZhou on 2016/11/24.
 */
public class SlideAdapter extends BaseRecycleAdapter {

    public SlideAdapter(Context context, List mList) {
        super(context, mList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            final MyViewHolder mMyViewHolder = (MyViewHolder) holder;
            mMyViewHolder.txt.setText("第" + String.valueOf(mList.get(position)) + "项");
            mMyViewHolder.layout.scrollTo(0, 0);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.item_recycler_txt);
            layout = (LinearLayout) itemView.findViewById(R.id.item_recycler_ll);
        }
    }
}
