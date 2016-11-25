package com.trailer.sliderecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        mSlideAdapter = new SlideAdapter(this,mList);
        mRecyclerView.setAdapter(mSlideAdapter);
    }

    protected void initRecycle(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

    }

