package com.blq.networksdk;

import java.util.HashMap;

import javax.net.ssl.X509TrustManager;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-11-15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public interface INetConfig {
    /**
     * @return log tag
     */
    String NetLogTag();


    X509TrustManager X509TrustManager();

    HashMap<String, String> globalHeaders();

    HashMap<String, String> globalParams();

}
