package org.exo.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
 }
