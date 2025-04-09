package com.kul.edutrackmockito.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/

@Getter
@Setter
public class GlobalApiResponse implements Serializable {
    private String message;
    private Object data;

    public GlobalApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
