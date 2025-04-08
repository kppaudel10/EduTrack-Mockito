package com.kul.edutrackmockito.config;

import java.io.Serializable;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/

public class GlobalApiResponse implements Serializable {
    private String message;
    private Object data;

    public GlobalApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
