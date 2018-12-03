package com.blq.networksdk;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import blq.ssnb.snbutil.SnbLog;
import okhttp3.ResponseBody;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/8/9
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * OkGo的请求回调
 * ================================================
 * </pre>
 */
abstract class BaseJsonCallBack<T extends BaseObjectBean> extends AbsCallback<T> {


    private Type mType;
    private Class<T> mTClass;

    public BaseJsonCallBack() {
    }

    public BaseJsonCallBack(Type type) {
        this.mType = type;
    }

    public BaseJsonCallBack(Class<T> mTClass) {
        this.mTClass = mTClass;
    }

    @Override
    public void onSuccess(Response<T> response) {
        onSuccess(response.body());
    }

    public abstract void onSuccess(T data);

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            throw new NullPointerException("网络请求失败");
        }
        String bodyString = body.string();
        String jsonString = decrypt(bodyString);
        SnbLog.se(NetworkManager.HTTP_LOG_TAG, "result:" + response.request().url() + "\n" + jsonString);

        T data;

        Gson gson = new Gson();

        Class<T> tClass = mTClass;
        Type tType = mType;

        if (tClass != null) {
            data = gson.fromJson(jsonString, tClass);
        } else if (tType != null) {
            data = gson.fromJson(jsonString, tType);
        } else {
            Type gType = getClass().getGenericSuperclass();
            tType = ((ParameterizedType) gType).getActualTypeArguments()[0];
            data = gson.fromJson(jsonString, tType);
        }

        return analysisData(data);
    }

    /**
     * 解密子类可以从写解密方法或者不解密
     *
     * @param bodyString 待解密的内容
     * @return 解密后的String内容
     */
    protected abstract String decrypt(String bodyString);

    protected T analysisData(T data) throws StatusErrorThrowable {
        if (data.getStatus() != NetworkManager.HTTP_REQUEST_SUCCESS_STATUS) {
            throw new StatusErrorThrowable(data.getStatus(), data.getMsg());
        }
        return data;
    }

    @Override
    public final void onError(Response<T> response) {
        super.onError(response);
        int errorStatus = -1;
        String errorMsg = "网络请求错误，请稍后再试";
        Throwable throwable = response.getException();
        if (throwable != null && throwable instanceof StatusErrorThrowable) {
            StatusErrorThrowable statusErrorThrowable = (StatusErrorThrowable) throwable;
            errorStatus = statusErrorThrowable.getStatus();
            errorMsg = statusErrorThrowable.getErrorMsg();
        }
        onError(errorStatus, errorMsg);
    }

    /**
     * 网络请求出错
     *
     * @param errorStatus 错误代码
     * @param errorMsg    错误信息
     */
    protected abstract void onError(int errorStatus, String errorMsg);
}
