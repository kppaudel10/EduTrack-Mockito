package com.kul.edutrackmockito.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalApiResponse implements Serializable {
    private String message;
    private Object data;
}
