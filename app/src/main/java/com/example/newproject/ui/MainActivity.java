package com.example.newproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newproject.R;
import com.example.newproject.data.DoHolderApi;
import com.example.newproject.data.ModelDo;
import com.example.newproject.data.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvActivity;
    private TextView tvPrice;
    private TextView tvType;
    private TextView tvLink;
    private ImageView img;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onBtnClick();
    }

    private void init(){
        tvActivity = findViewById(R.id.activity);
        tvPrice = findViewById(R.id.price);
        tvType = findViewById(R.id.type);
        tvLink = findViewById(R.id.link);
        img = findViewById(R.id.heart);
        btn = findViewById(R.id.btn);
    }

    private void onBtnClick(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoHolderApi doHolderApi = RetrofitBuilder.getInstance();

                Call<ModelDo> modelDoCall = doHolderApi.getActivities();
                modelDoCall.enqueue(new Callback<ModelDo>() {
                    @Override
                    public void onResponse(Call<ModelDo> call, Response<ModelDo> response) {
                        if (response.isSuccessful()){
                            ModelDo modelDo = response.body();
                            tvActivity.setText("Acivity: " + modelDo.getActivity());
                            tvPrice.setText("Price: " + modelDo.getPrice());
                            tvType.setText("Type: " + modelDo.getType());
                            tvLink.setText("Link: " + modelDo.getLink());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelDo> call, Throwable t) {

                    }
                });
            }
        });

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = tvLink.getText().toString();
                Log.i("vhukl", value);

                if (value.length() > 6){
                    String link = value.split(" ")[1];
                    Log.i("vhukl", value);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            }
        });

    }
}