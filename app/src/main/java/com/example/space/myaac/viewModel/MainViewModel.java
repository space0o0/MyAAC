package com.example.space.myaac.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.space.myaac.constantSet.ConstantSet;
import com.example.space.myaac.entity.BaseEntity;
import com.example.space.myaac.entity.GankEntity;
import com.example.space.myaac.entity.ResultBean;
import com.example.space.myaac.service.GankService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by space on 2018/3/20.
 */

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<BaseEntity<ResultBean>> mObservableGanks;

    public MainViewModel(Application application) {
        super(application);

        mObservableGanks = new MutableLiveData<>();

        getGank();
    }

    public LiveData<BaseEntity<ResultBean>> getGanks() {
        return mObservableGanks;
    }

    private void getGank() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantSet.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GankService gankService = retrofit.create(GankService.class);

        gankService.getGank().enqueue(new Callback<BaseEntity<ResultBean>>() {
            @Override
            public void onResponse(Call<BaseEntity<ResultBean>> call, Response<BaseEntity<ResultBean>> response) {
                mObservableGanks.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseEntity<ResultBean>> call, Throwable t) {


            }
        });
    }
}
