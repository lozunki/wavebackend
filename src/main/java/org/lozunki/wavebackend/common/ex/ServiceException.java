package org.lozunki.wavebackend.common.ex;

import lombok.Getter;
import org.lozunki.wavebackend.common.web.ServiceCode;

public class ServiceException extends RuntimeException {
    @Getter
    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }
}
