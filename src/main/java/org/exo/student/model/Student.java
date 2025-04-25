package org.exo.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "etudiant")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le champs ne doit pas être vide")
    @Column(name = "prenom")
    private String firstName;
    @NotBlank(message = "Le champs ne doit pas être vide")
    @Column(name = "nom")
    private String lastName;
    private int age;
    @NotBlank(message = "Le champs ne doit pas être vide")
    private String email;
 }
