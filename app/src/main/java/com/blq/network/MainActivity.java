package com.blq.network;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blq.networksdk.AbsJsonCallBack;
import com.blq.networksdk.NetProxy;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView showView;
    private TextView btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showView = findViewById(R.id.tv_login_show);
        btnView = findViewById(R.id.tv_login_btn);

        btnView.setOnClickListener(e -> Login());

        findViewById(R.id.tv_new_btn).setOnClickListener(this::customBase);
    }


    private void Login() {
        NetProxy.<List<UserBean>>get(DemoHttpInterface.DemoInterface.DEMO_1)
                .params("name", "ssnb")
                .params("password", "123456")
                .execute(new AbsJsonCallBack<List<UserBean>>() {
                    @Override
                    protected void onSuccess(List<UserBean> data) {
                        showView.setText("成功:" + data.toString());
                    }

                    @Override
                    protected void onError(int errorStatus, String errorMsg) {
                        showView.setText("失败:" + errorStatus + ":" + errorMsg);
                    }

                    @Override
                    protected String decrypt(String bodyString) {
                        return bodyString;
                    }
                });

    }

    private void customBase(View v) {
        NetProxy.<List<UserBean>>get(DemoHttpInterface.DemoInterface.DEMO_2)
                .params("name", "ssnb")
                .params("password", "123456")
                .execute(new AbsJsonCallBack<List<UserBean>>() {
                    @Override
                    protected void onSuccess(List<UserBean> data) {
                        showView.setText("成功:" + data.toString());
                    }

                    @Override
                    protected void onError(int errorStatus, String errorMsg) {
                        showView.setText("失败:" + errorStatus + ":" + errorMsg);
                    }

                    @Override
                    protected String decrypt(String bodyString) {
                        return bodyString;
                    }

                    @Override
                    protected Class<?> getBaseClass() {
                        return NewBaseObjectBean.class;
                    }
                });


    }
}
