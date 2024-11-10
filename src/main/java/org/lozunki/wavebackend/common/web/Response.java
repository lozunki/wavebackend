package org.lozunki.wavebackend.common.web;

import lombok.Data;
import org.lozunki.wavebackend.common.ex.ServiceException;

@Data
public class Response {
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
    private Object data;
    
    public static Response ok() {
        // Response Response = new Response();
        // Response.setState(ServiceCode.OK.getValue());
        // return Response;
        return ok(null);
    }

    public static Response ok(Object data) {
        Response Response = new Response();
        Response.setState(ServiceCode.OK.getValue());
        Response.setData(data);
        return Response;
    }

    public static Response fail(ServiceException e) {
        // Response Response = new Response();
        // Response.setState(e.getServiceCode().getValue());
        // Response.setMessage(e.getMessage());
        // return Response;
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static Response fail(ServiceCode serviceCode, String message) {
        Response Response = new Response();
        Response.setState(serviceCode.getValue());
        Response.setMessage(message);
        return Response;
    }
}
