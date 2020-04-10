package com.blq.networksdk;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * /**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2018/8/12
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * networkSdk 请求的回调 隔绝app 与网络框架 进行解耦
 * ================================================
 * </pre>
 */

public abstract class AbsJsonCallBack<T> {

    private BaseJsonCallBack<IBaseObject<T>> mJsonCallBack;

    BaseJsonCallBack<IBaseObject<T>> getJsonCallBack() {
        if (mJsonCallBack == null) {
            throw new IllegalArgumentException("子类使用时需要继承并实现super()方法");
        }
        return mJsonCallBack;
    }

    public AbsJsonCallBack() {

        ParameterizedType gType = (ParameterizedType) getClass().getGenericSuperclass();
        Type tType = gType.getActualTypeArguments()[0];
        ParameterizedTypeImpl type = new ParameterizedTypeImpl(getBaseClass(), tType);

        mJsonCallBack = new BaseJsonCallBack<IBaseObject<T>>(type) {
            @Override
            public void onSuccess(IBaseObject<T> data) {
                AbsJsonCallBack.this.onSuccess(data.getData());
            }

            @Override
            protected void onError(int errorStatus, String errorMsg) {
                AbsJsonCallBack.this.onError(errorStatus, errorMsg);
            }

            @Override
            protected String decrypt(String bodyString) {
                return AbsJsonCallBack.this.decrypt(bodyString);
            }
        };
    }

    /**
     * 获得外壳对象类
     *
     * @return
     */
    protected Class getBaseClass() {
        return BaseObjectBean.class;
    }

    protected abstract void onSuccess(T data);

    protected abstract void onError(int errorStatus, String errorMsg);

    protected abstract String decrypt(String bodyString);

    private static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type... args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

    }

}
