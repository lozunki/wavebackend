package org.lozunki.wavebackend.core.ex.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.common.ex.ServiceException;
import org.lozunki.wavebackend.common.web.Response;
import org.lozunki.wavebackend.common.web.ServiceCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Response handleServiceException(ServiceException e) {
        log.debug("全局异常处理器开始处理ServiceException");
        log.debug("ServiceException详细信息：", e);
        return Response.fail(e);
    }

    @ExceptionHandler
    public Response handleBindException(BindException e) {
        log.debug("全局异常处理器开始处理BindException");
        // 以下代码将提示所有格式错误的文本
        // StringJoiner stringJoiner = new StringJoiner("，", "请求参数格式错误，", "！");
        // List<FieldError> fieldErrors = e.getFieldErrors();
        // for (FieldError fieldError : fieldErrors) {
        //     String defaultMessage = fieldError.getDefaultMessage();
        //     stringJoiner.add(defaultMessage);
        // }
        // String message = stringJoiner.toString();
        // log.warn(message);
        // return Response.fail(ServiceCode.ERROR_BAD_REQUEST, message);
        // 以下代码将提示随机的某个格式错误的文本
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求参数格式错误，");
        stringBuilder.append(e.getFieldError().getDefaultMessage());
        stringBuilder.append("！");
        String message = stringBuilder.toString();
        log.warn(message);
        return Response.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public Response handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("全局异常处理器开始处理ConstraintViolationException");
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        String message = stringBuilder.toString();
        return Response.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler({
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class
    })
    public Response handleAuthenticationException(AuthenticationException e) {
        log.debug("全局异常处理器开始处理AuthenticationException");
        log.debug("异常类型：{}", e.getClass().getName());
        String message = "登录失败，用户名或密码错误！";
        return Response.fail(ServiceCode.ERROR_UNAUTHORIZED, message);
    }

    @ExceptionHandler
    public Response handleDisabledException(DisabledException e) {
        log.debug("全局异常处理器开始处理DisabledException");
        String message = "登录失败，此账号已经被禁用！";
        return Response.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
    }

    @ExceptionHandler
    public Response handleAccessDeniedException(AccessDeniedException e) {
        log.debug("全局异常处理器开始处理AccessDeniedException");
        String message = "操作失败，当前登录的账号无此操作权限！";
        return Response.fail(ServiceCode.ERROR_FORBIDDEN, message);
    }

    @ExceptionHandler
    public Response handleThrowable(Throwable e) {
        log.debug("全局异常处理器开始处理Throwable");
        log.debug("异常跟踪信息如下：", e);
        String message = "服务器忙，请稍后再试！【看到这句时，你应该检查服务器端的控制台，并在全局异常处理器中添加处理异常的方法】";
        return Response.fail(ServiceCode.ERROR_UNKNOWN, message);
    }


}
