package com.blq.networksdk;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import blq.ssnb.snbutil.SnbLog;


/**
 * ================================================
 * 作者: SSNB
 * 日期: 2018/5/31
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 用于服务器前缀的生成的工具
 * ================================================
 */
public class NetSdkUtil {

    public static String getPrefixHttp(String host, int port, String serviceName) {
        return String.format(Locale.CHINA, "http://%s:%d%s", host, port, serviceName);
    }

    public static String getPrefixHttps(String host, int port, String serviceName) {
        return String.format(Locale.CHINA, "https://%s:%d%s", host, port, serviceName);
    }

    /**
     * 将参数转换为json后加密的string ,使用addparam("json",returnString)
     *
     * @param keys
     * @param values
     * @return
     */
    public static String getParams(String[] keys, String[] values) {
        return getParams(keys, values, null);
    }

    public static String getParams(String[] keys, String[] values, String cryptKey) {
        Map<String, String> map = new HashMap<>(5);
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return paramsTranslation(cryptKey, map);
    }

    private static String paramsTranslation(String cryptKey, Map<String, String> paramsMap) {
        String json = new JSONObject(paramsMap).toString();
        SnbLog.se(NetworkManager.HTTP_LOG_TAG, "request:" + json);
        String encryData;
        if (null == cryptKey) {
            encryData = CryptLib.enctry(json);
        }else{
            encryData = CryptLib.enctry(cryptKey,json);
        }
        SnbLog.se(NetworkManager.HTTP_LOG_TAG, "request-enctry:" + encryData);
        return encryData;
    }
}
