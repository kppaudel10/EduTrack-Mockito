package com.kul.edutrackmockito.pojo;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@Getter
@Setter
public class StudentReqPojo {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
}

