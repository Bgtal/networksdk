package com.blq.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.blq.networksdk.AbsJsonCallBack;
import com.blq.networksdk.NetProxy;

public class MainActivity extends AppCompatActivity {

    private TextView showView;
    private TextView btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showView = findViewById(R.id.tv_login_show);
        btnView = findViewById(R.id.tv_login_btn);

        btnView.setOnClickListener(e-> Login());
    }


    private void Login(){
        NetProxy.<UserBean>get(DemoHttpInterface.DemoInterface.DEMO_LOGIN)
                .params("name","ssnb")
                .params("password","123456")
                .execute(new AbsJsonCallBack<UserBean>() {
                    @Override
                    protected void onSuccess(UserBean data) {
                        showView.setText("成功:"+data.toString());
                    }

                    @Override
                    protected void onError(int errorStatus, String errorMsg) {
                        showView.setText("失败:"+errorStatus+":"+errorMsg);
                    }

                    @Override
                    protected String decrypt(String bodyString) {
                        return bodyString;
                    }
                });
    }
}
