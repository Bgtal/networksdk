package com.blq.networksdk;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import blq.ssnb.snbutil.SnbLog;
import okhttp3.OkHttpClient;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/8/9
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 网络库的配置
 * ================================================
 * </pre>
 */
public class NetworkManager {

    public static final String HTTP_LOG_TAG = "snblog_http";
    public static final int HTTP_REQUEST_SUCCESS_STATUS = 10000;

    /**
     * 第一步注册网络库
     * @param application
     */
    public static void initNetwork(Application application){
        SnbLog.getBuilder(HTTP_LOG_TAG).isOpen(BuildConfig.DEBUG).setTag(HTTP_LOG_TAG);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //------------添加LOG相关------start--------------
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(HTTP_LOG_TAG);
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        //添加OkGo默认debug日志
        builder.addInterceptor(loggingInterceptor);
        //------------添加LOG相关------end--------------

        //------------超时设置------start--------------
        //超时时间设置，默认60秒
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //------------超时设置------end--------------

        //使用数据库保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(application)));

        // ------------https相关设置------start--------------
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        // ------------https相关设置------end--------------
        OkGo.getInstance()
                //必须调用初始化
                .init(application)
                //建议设置OkHttpClient，不设置会使用默认的
                .setOkHttpClient(builder.build())
                //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                //全局统一缓存时间，默认永不过期，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .setRetryCount(3);
    }

    private static Set<Class<? extends ServiceToggleable>> hashSet= new HashSet<>();
    private static boolean isFirstToggle = false;
    private static String lastToggleHost = "";
    private static int lastTogglePost = 0;
    private static boolean lastToggleIsDebug = true;

    /**
     * 第二部将需要网络接口注册
     * @param toggleableClass
     */
    public static void registerServiceToggleable(Class<? extends ServiceToggleable> toggleableClass){
        hashSet.add(toggleableClass);
        if(!isFirstToggle){
            toToggle(toggleableClass,lastToggleHost,lastTogglePost,lastToggleIsDebug);
        }
    }

    /**
     * 切换服务
     * 可用于
     * @param host host
     * @param post post
     * @param isDebug 是否测试
     */
    public static void serviceToggle(String host, int post , boolean isDebug){
        lastToggleHost = host;
        lastTogglePost = post;
        lastToggleIsDebug = isDebug;

        for (Class<? extends ServiceToggleable> aClass : hashSet) {
            toToggle(aClass,host,post,isDebug);
        }

        isFirstToggle = false;
    }

    /**
     * 分发到子类实现切换接口
     * @param aClass 注册的class
     * @param host host
     * @param post post
     * @param isDebug isDebug
     */
    private static void toToggle(Class<? extends ServiceToggleable> aClass, String host, int post , boolean isDebug){
        try {
            ServiceToggleable toggleable = aClass.newInstance();
            toggleable.toggleServiceAddress(host, post, isDebug);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
