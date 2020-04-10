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
    public void toggleServiceAddress(String host, int post, String serviceName, boolean isDebug) {
        //当接口切换的时候回调用该方法
        String demoService;
        if (isDebug) {
            //假如是debug 的时候 用的是  Http 请求
            demoService = NetSdkUtil.getPrefixHttp(host, post, serviceName + "/demo");
        } else {
            //release 用的是Https 请求
            demoService = NetSdkUtil.getPrefixHttps(host, post, serviceName + "/demo");
        }
        //我们可以在这个方法里面进行请求前缀的修改
        DemoInterface.init(demoService);
    }

    public static class DemoInterface {
        public static String DEMO_1;
        public static String DEMO_2;

        private static void init(String service) {

            DEMO_1 = service + "/demo1";
            DEMO_2 = service + "/demo2";
        }
    }
}
