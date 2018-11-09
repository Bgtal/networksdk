package com.blq.network;

import android.app.Application;

import com.blq.networksdk.NetworkManager;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2018/11/9
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class MApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //1.先初始化
        NetworkManager.initNetwork(this);
        //2.将接口注册到NetWorkManger
        NetworkManager.registerServiceToggleable(DemoHttpInterface.class);
        //3.切换服务器
        NetworkManager.serviceToggle("106.15.194.131",3000,BuildConfig.DEBUG);

    }
}
