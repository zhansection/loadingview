package com.zhanke.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhanke.loadingview.StatusView;
import com.zhanke.loadingview.StatusViewBuilder;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StatusView loading_view;
    private Button btn_loading;
    private Button btn_content;
    private Button btn_error;
    private Button btn_empty;
    private Button btn_go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading_view = (StatusView) findViewById(R.id.statusView);
        btn_loading = (Button) findViewById(R.id.btn_loading);
        btn_content = (Button) findViewById(R.id.btn_content);
        btn_error = (Button) findViewById(R.id.btn_error);
        btn_empty = (Button) findViewById(R.id.btn_empty);
        btn_go = (Button) findViewById(R.id.btn_go);

        btn_loading.setOnClickListener(this);
        btn_content.setOnClickListener(this);
        btn_error.setOnClickListener(this);
        btn_empty.setOnClickListener(this);
        btn_go.setOnClickListener(this);

        loading_view.config(new StatusViewBuilder.Builder().setOnErrorRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("1111", "点击了");
                loading_view.showLoadingView();
            }
        }).setOnEmptyRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("1111", "空页面");
                loading_view.showLoadingView();
            }
        })
                .build());

        loading_view.showLoadingView();//显示加载界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading_view.showContentView();
            }
        }, 100);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loading:
                loading_view.showLoadingView();//显示加载界面
                break;
            case R.id.btn_content:
                loading_view.showContentView();//显示内容界面
                break;
            case R.id.btn_error:
                loading_view.showErrorView();//显示错误界面
                break;
            case R.id.btn_empty:
                loading_view.showEmptyView();//显示空数据界面
                break;
            case R.id.btn_go:
                startActivity(new Intent(this,Main2Activity.class));
                break;
        }
    }
}
