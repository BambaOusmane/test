package com.school.school.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String niveau;
    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;


//    @ManyToMany(mappedBy = "option",fetch = FetchType.EAGER)
//    private List<ScolariteExaamen> scolariteExamens;
}
