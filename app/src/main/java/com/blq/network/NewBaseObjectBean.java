package com.blq.network;

import com.blq.networksdk.IBaseObject;

import java.io.Serializable;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2020-04-10
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class NewBaseObjectBean<T> implements IBaseObject<T>, Serializable {

    private boolean isSuccess;
    private String message;
    private int count;
    private T data;

    @Override
    public int getStatus() {
        if (isSuccess) {
            return 10000;
        }
        return -1;
    }

    @Override
    public void setStatus(int status) {
        this.isSuccess = status == 1000;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public void setMsg(String msg) {
        this.message = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
