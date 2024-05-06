package com.school.school.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.school.school.Models.enums.StatusUsers;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import static com.school.school.Models.enums.StatusUsers.INACTIF;
@CrossOrigin("*")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class User   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String nom;
    @Column(length = 20)
    private String prenom;
    @Column(length = 20)
    private String adresse;
    @Column(unique = true)
    private String email;
    @Column(length = 13)
    private Long phone;
    @Column(length = 40)
    private String password;
    @Column(unique = true,length = 15)
    private String matricule;
    private Date dateInscription;
    private String login;
//    @Lob
//    @Column(length = 1000000)
//    private byte [] displayPicture;
    @Enumerated(EnumType.STRING)
    private StatusUsers status=INACTIF;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;



}







