package org.lozunki.wavebackend.common.web;

import lombok.Data;
import org.lozunki.wavebackend.common.ex.ServiceException;

@Data
public class Response<T> {
    /**
     * 响应的业务状态码值
     */
    private Integer state;
    /**
     * 操作失败时的描述文本
     */
    private String message;
    /**
     * 操作成功时的响应数据
     */
    private T data;  // 使用泛型 T 而不是 Object

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setState(ServiceCode.OK.getValue());
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static <T> Response<T> fail(ServiceCode serviceCode, String message) {
        Response<T> response = new Response<>();
        response.setState(serviceCode.getValue());
        response.setMessage(message);
        return response;
    }
}
