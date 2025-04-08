package com.kul.edutrackmockito.pojo;

import lombok.*;

import java.util.Date;

/**
 * author: Kul Paudel
 * createdDate: 2025-04-06
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentReqPojo {
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String contact;
}

