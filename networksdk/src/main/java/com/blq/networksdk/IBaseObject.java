package com.blq.networksdk;

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
public interface IBaseObject<T> extends Serializable {

    int successStatusCode();

    int getStatus();

    void setStatus(int status);

    T getData();

    void setData(T data);

    String getMsg();

    void setMsg(String msg);
}
