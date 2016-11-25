package com.trailer.sliderecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SlideAdapter.ClickListener {

    private MyRecycleView mRecyclerView;
    private SlideAdapter mSlideAdapter;
    private List mList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (MyRecycleView) findViewById(R.id.rv_container);
        initRecycle(mRecyclerView);
        for (int i =0;i<=10;i++){
            mList.add(i);
        }
        mSlideAdapter = new SlideAdapter(this,mList,this);
        mRecyclerView.setAdapter(mSlideAdapter);
    }

    protected void initRecycle(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

    @Override
    public void click(int position) {
        Toast.makeText(this,"点击了"+position+"项",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete(int position) {
        mList.remove(position);
        mSlideAdapter.updateListView(mList);
    }
}

