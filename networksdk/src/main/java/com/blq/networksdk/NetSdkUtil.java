package com.blq.networksdk;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 格式化网络前缀(http)
     * @param host 例: www.baidu.com
     * @param port 例: 8080
     * @param serviceName 例:/Login
     * @return 例:http://www.baidu.com:8080/Login
     */
    public static String getPrefixHttp(String host, int port, String serviceName) {
        return String.format(Locale.CHINA, "http://%s:%d%s", host, port, serviceName);
    }

    /**
     * 格式化网络前缀(https)
     * @param host 例: www.baidu.com
     * @param port 例: 443
     * @param serviceName 例:/Login
     * @return 例:https://www.baidu.com:443/Login
     */
    public static String getPrefixHttps(String host, int port, String serviceName) {
        return String.format(Locale.CHINA, "https://%s:%d%s", host, port, serviceName);
    }

    /**
     * 获得参数对象
     * 该对象的toJsonString 方法返回String 不经过加密
     * @return 参数对象
     */
    public static Params paramsFactory() {
        return new Params(null);
    }

    /**
     * 获得参数对象
     * 若加密key为null 同{@link #paramsFactory()}
     * 否者该对象的toJsonString 方法返回String 经过加密
     * @param cryptKey 加密key
     * @return 参数对象
     */
    public static Params paramsFactory(String cryptKey) {
        return new Params(cryptKey);
    }

    /**
     * 参数对象
     */
    public static class Params {
        private Map<String, Object> params;
        private String cryptKey;

        private Params(String cryptKey) {
            params = new HashMap<>();
            this.cryptKey = cryptKey;
        }

        public Params addParam(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Params addParam(String key,int value){
            params.put(key,value);
            return this;
        }

        public Params addParam(String key,long value){
            params.put(key,value);
            return this;
        }

        public Params addParam(String key, float value) {
            params.put(key, value);
            return this;
        }

        public Params addParam(String key, boolean value) {
            params.put(key, value);
            return this;
        }

        public Params addParam(String key, List<Params> list) {
            List<Map<String, Object>> ls = new ArrayList<>();
            for (Params params : list) {
                ls.add(params.params);
            }
            params.put(key, ls);
            return this;
        }

        public Params addParamString(String key, List<String> list) {
            params.put(key, list);
            return this;
        }
        public Params addParamInt(String key, List<Integer> list) {
            params.put(key, list);
            return this;
        }

        public Params addParamLong(String key, List<Long> list) {
            params.put(key, list);
            return this;
        }
        public Params addParam(Params params) {
            this.params.putAll(params.params);
            return this;
        }

        public Params addParam(String key, Params params) {
            this.params.put(key, params.params);
            return this;
        }

        public Params replaceParams(Params params) {
            this.params.clear();
            this.params.putAll(params.params);
            return this;
        }


        @Deprecated()
        public Params addParams(Map<String, String> params) {
            this.params.putAll(params);
            return this;
        }

        @Deprecated
        public Params replaceParams(Map<String, String> params) {
            this.params.clear();
            this.params.putAll(params);
            return this;
        }

        public String toJsonString() {
            JSONObject jsonObject = new JSONObject(params);
            String json = jsonObject.toString();
            SnbLog.se(NetworkManager.HTTP_LOG_TAG, "request:" + json);
            if (cryptKey != null) {
                json = CryptLib.enctry(cryptKey, json);
                SnbLog.se(NetworkManager.HTTP_LOG_TAG, "request-enctry:" + json);
            }
            return json;
        }
    }
}
