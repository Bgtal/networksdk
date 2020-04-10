package com.blq.networksdk;

import java.io.Serializable;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/8/9
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 网络请求基类Bean
 * ================================================
 * </pre>
 */
class BaseObjectBean<T> implements IBaseObject<T>, Serializable {

    /**
     * 状态码
     */
    private int status;
    /**
     * 返回的数据对象
     */
    private T data;
    /**
     * 状态文字描述
     */
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
