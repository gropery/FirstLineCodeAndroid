package com.example.sendhttprequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;
    final static String address = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_request){

            //sendWithHttpURL();
            sendWithOkHttp();
        }
    }

    private void sendWithHttpURL(){
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                // 在这里根据返回内容执行具体的逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里进行UI操作, 将结果显示到界面上
                        responseText.setText(response);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                // 在这里对异常情况进行处理
                e.printStackTrace();
            }
        });
    }

    private void sendWithOkHttp(){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // 得到服务器返回的具体内容
                String responseData = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里进行UI操作, 将结果显示到界面上
                        responseText.setText(responseData);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 在这里对异常情况进行处理
                e.printStackTrace();
            }
        });
    }
}