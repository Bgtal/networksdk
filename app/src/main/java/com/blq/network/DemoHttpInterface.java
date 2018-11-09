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
 *      添加描述
 * ================================================
 * </pre>
 */
public class DemoHttpInterface implements ServiceToggleable {

    @Override
    public void toggleServiceAddress(String host, int post, boolean isDebug) {
        String service = NetSdkUtil.getPrefixHttp(host,post,"/mock/36/mobile");
        DemoInterface.init(service);
    }

    public static class DemoInterface{
        public static String DEMO_LOGIN;

        private static void init(String service){
            DEMO_LOGIN = service + "/login";
        }
    }
}
