package org.exo.student.model;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String firstName;
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String lastName;
    private int age;
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String email;
 }
