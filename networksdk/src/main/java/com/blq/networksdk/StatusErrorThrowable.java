package com.blq.networksdk;


/**
 * ================================================
 * 作者: SSNB
 * 日期: 2018/6/4
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 网络请求状态错误异常
 * ================================================
 */
public class StatusErrorThrowable extends Exception {
    private int status ;
    private String errorMsg ;

    /**
     * @param status 状态码
     * @param message 错误内容
     */
    public StatusErrorThrowable(int status, String message) {
        super(message);
        this.status = status;
        this.errorMsg = message;
    }

    /**
     * 错误状态码
     * @return 非10000的状态码
     */
    public int getStatus(){
        return  status;
    }

    /**
     *
     * @return
     */
    public String getErrorMsg(){
        return errorMsg;
    }
}
