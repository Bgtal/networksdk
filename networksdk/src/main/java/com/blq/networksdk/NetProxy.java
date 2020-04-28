package com.blq.networksdk;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * /**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2018/8/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 网络请求操作的代理库
 * ================================================
 * </pre>
 */

public class NetProxy {

    /**
     * 取消网络请求
     *
     * @param object 请求tag
     */
    public static void cancelTag(Object object) {
        OkGo.getInstance().cancelTag(object);
    }

    /**
     * post 请求对象
     *
     * @param url 请求地址
     * @param <T> 返回对象
     * @return post请求构建体
     */
    public static <T> PostBuilder<T> post(String url) {
        return new PostBuilder<T>(url);
    }

    /**
     * get 请求对象
     *
     * @param url 请求地址
     * @param <T> 返回对象
     * @return post请求构建体
     */
    public static <T> GetBuilder<T> get(String url) {
        return new GetBuilder<T>(url);
    }

    public static class PostBuilder<T> {
        PostRequest<IBaseObject<T>> mRequest;

        private PostBuilder(String url) {
            mRequest = OkGo.post(url);
        }

        public PostBuilder<T> tag(Object tag) {
            mRequest.tag(tag);
            return this;
        }

        public PostBuilder<T> cacheKey(String cacheKey) {
            mRequest.cacheKey(cacheKey);
            return this;
        }

        /**
         * 传入 -1 表示永久有效,默认值即为 -1
         *
         * @param time 缓存时间
         */
        public PostBuilder<T> cacheTime(long time) {
            mRequest.cacheTime(time);
            return this;
        }

        public PostBuilder<T> headers(String key, String value) {
            mRequest.headers(key, value);
            return this;
        }

        public PostBuilder<T> params(String key, String value) {
            mRequest.params(key, value);
            return this;
        }

        public PostBuilder<T> paramsValues(String key, List<String> values) {
            mRequest.addUrlParams(key, values);
            return this;
        }

        public PostBuilder<T> params(String key, File file) {
            mRequest.params(key, file);
            return this;
        }

        public PostBuilder<T> paramsFiles(String key, List<File> files) {
            mRequest.addFileParams(key, files);
            return this;
        }

        public String execute() throws IOException {
            Response body = mRequest.execute();
            return body.toString();
        }

        public void execute(AbsJsonCallBack<T> callBack) {
            mRequest.execute(callBack.getJsonCallBack());
        }

    }

    public static class GetBuilder<T> {
        GetRequest<IBaseObject<T>> mRequest;
//        GetRequest<BaseObjectBean<T>> mRequest;

        private GetBuilder(String url) {
            mRequest = OkGo.get(url);
        }

        public GetBuilder<T> tag(Object tag) {
            mRequest.tag(tag);
            return this;
        }

        public GetBuilder<T> cacheKey(String cacheKey) {
            mRequest.cacheKey(cacheKey);
            return this;
        }

        /**
         * 传入 -1 表示永久有效,默认值即为 -1
         *
         * @param time 缓存时间
         */
        public GetBuilder<T> cacheTime(long time) {
            mRequest.cacheTime(time);
            return this;
        }

        public GetBuilder<T> headers(String key, String value) {
            mRequest.headers(key, value);
            return this;
        }

        public GetBuilder<T> params(String key, String value) {
            mRequest.params(key, value);
            return this;
        }

        public GetBuilder<T> paramsValues(String key, List<String> values) {
            mRequest.addUrlParams(key, values);
            return this;
        }

        public String execute() throws IOException {
            Response body = mRequest.execute();
            return body.toString();
        }

        public void execute(AbsJsonCallBack<T> callBack) {
            mRequest.execute(callBack.getJsonCallBack());
        }
    }

}
