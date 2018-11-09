package com.blq.network;

import com.blq.networksdk.NetSdkUtil;
import com.blq.networksdk.ServiceToggleable;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2018/11/9
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 这样可以动态切换接口所在服务器，可以不用编译在APP端切换正式版和测试版
 * ================================================
 * </pre>
 */
public class DemoHttpInterface implements ServiceToggleable {

    @Override
    public void toggleServiceAddress(String host, int post, boolean isDebug) {
        //当接口切换的时候回调用该方法
        String demoService;
        String demo2Service;
        if(isDebug){
            //假如是debug 的时候 用的是  Http 请求
            demoService = NetSdkUtil.getPrefixHttp(host,post,"/mock/36/mobile");
            //或者请求的接口服务名称修改 比如debug 是 /debug/mobile
            demo2Service = NetSdkUtil.getPrefixHttp(host,post,"/mock/debug/mobile");
        }else{
            //release 用的是Https 请求
            demoService = NetSdkUtil.getPrefixHttps(host,post,"/mock/36/mobile");
            //release 用的是 /release/mobile
            demo2Service = NetSdkUtil.getPrefixHttp(host,post,"/mock/release/mobile");
        }
        //我们可以在这个方法里面进行请求前缀的修改
        DemoInterface.init(demoService);
    }

    public static class DemoInterface{
        /**
         * 登录接口
         */
        public static String DEMO_LOGIN;

        private static void init(String service){
            DEMO_LOGIN = service + "/login";
        }
    }

    public static class DemoInterFace2{
        public static String DEMO2_INFO;

        private static void init(String service){
            DEMO2_INFO = service + "/xxxxxx";
        }
    }
}
