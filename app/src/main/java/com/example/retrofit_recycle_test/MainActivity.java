package com.example.retrofit_recycle_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Bean.DataBean> dataList = new ArrayList<>();
    private RecyclerView recycler_View;
    private Adapter adapter;
    private String url = "ios/cf/dish_list.php?stage_id=1&limit=20&page=1";
    private static String TAG = "zsbenn";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        initview();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.qubaobei.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRetrofitGet iRetrofitGet = retrofit.create(IRetrofitGet.class);
        Call<Bean> call = iRetrofitGet.getcall(url);
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                   Bean bean = response.body();
                Log.d(TAG, "onResponse: ok");
                   dataList.addAll(bean.getData());
                Log.d(TAG, "onResponse: "+dataList.size());
                   adapter.notifyDataSetChanged();
                Log.d(TAG, "onResponse: ");
            }
            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Log.d(TAG, "onFailure: ok"+call.request().url());
            }
        });
    }

    private void initview(){
        recycler_View = (RecyclerView)findViewById(R.id.recyvle_View);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_View.setLayoutManager(manager);
        adapter = new Adapter(getApplicationContext(),dataList);
        recycler_View.setAdapter(adapter);
    }
}
