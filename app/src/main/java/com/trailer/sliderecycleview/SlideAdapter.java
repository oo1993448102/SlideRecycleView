package com.trailer.sliderecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EchoZhou on 2016/11/24.
 */
public class SlideAdapter extends BaseRecycleAdapter {

    private ClickListener listener;

    public SlideAdapter(Context context, List mList, ClickListener listener) {
        super(context, mList);
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            final MyViewHolder mMyViewHolder = (MyViewHolder) holder;
            mMyViewHolder.txt.setText("第" + String.valueOf(mList.get(position)) + "项");
            mMyViewHolder.layout.scrollTo(0, 0);
            mMyViewHolder.txt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Log.i("click", "touch");

                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();

                    int xDown = 0, yDown = 0, xUp, yUp;

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            xDown = x;
                            yDown = y;
                            Log.i("click", "down" + xDown + yDown);

                            break;
                        case MotionEvent.ACTION_UP:

                            xUp = x;
                            yUp = y;
                            Log.i("click", "up" + xUp + yUp);
                            int dx = xUp - xDown;
                            int dy = yUp - yDown;
                            Log.i("click", dx + " " + dy);
                            if (Math.abs(dy) < ViewConfiguration.get(mContext).getScaledTouchSlop() && Math.abs(dx) < ViewConfiguration.get(mContext).getScaledTouchSlop()) {
                                if (listener != null) {
                                    listener.click(position);
                                }

                            }
                            break;
                    }
                    return false;
                }
            });
            mMyViewHolder.rv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.delete(position);
                    }
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public LinearLayout layout;
        public RelativeLayout rv_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.item_recycler_txt);
            layout = (LinearLayout) itemView.findViewById(R.id.item_recycler_ll);
            rv_delete = (RelativeLayout) itemView.findViewById(R.id.rv_delete);
        }
    }

    public interface ClickListener {
        void click(int position);

        void delete(int position);
    }
}
