package com.example.space.myaac.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.space.myaac.R;
import com.example.space.myaac.base.BaseRecyclerAdapter;
import com.example.space.myaac.base.BaseRecyclerViewHolder;
import com.example.space.myaac.constantSet.ConstantSet;
import com.example.space.myaac.entity.GankEntity;
import com.example.space.myaac.service.GankService;
import com.example.space.myaac.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MainViewModel mainViewModel;
    BaseRecyclerAdapter<GankEntity.ResultsBean> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        subscribeUI(mainViewModel);

    }

    private void subscribeUI(MainViewModel viewModel) {
        viewModel.getGanks().observe(this, new Observer<GankEntity>() {
            @Override
            public void onChanged(@Nullable GankEntity gankEntity) {

                if (adapter == null) {
                    initAdapter(gankEntity.getResults());
                } else {
                    adapter.setData(gankEntity.getResults());
                }
            }
        });
    }

    private void initAdapter(List<GankEntity.ResultsBean> list) {

        adapter = new BaseRecyclerAdapter<GankEntity.ResultsBean>(this, list) {
            @Override
            public int getItemLayoutID(int viewType) {
                return R.layout.item_main;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, GankEntity.ResultsBean item) {
                holder.getTextView(R.id.desc).setText(item.getDesc());
                holder.getTextView(R.id.url).setText(item.getUrl());
                holder.getTextView(R.id.id).setText(item.get_id());
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);

    }

}

/**
 * 暂时没用的
 */
class MyObserver implements LifecycleObserver {

    private String TAG = getClass().getSimpleName() + "@#";

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectUI() {
        Log.i(TAG, "connectUI: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disConnectUI() {
        Log.i(TAG, "disConnectUI: ");
    }
}