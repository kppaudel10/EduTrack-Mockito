package com.kul.edutrackmockito.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@Getter
@Setter
@Builder
public class StudentResPojo {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
}

