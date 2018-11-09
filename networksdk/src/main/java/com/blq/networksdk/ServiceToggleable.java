package com.blq.networksdk;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期: 2018/8/10
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 服务切换接口，当服务切换的时候回调用该接口
 * ================================================
 * </pre>
 */
public interface ServiceToggleable {
    void toggleServiceAddress(String host, int post, boolean isDebug);
}
