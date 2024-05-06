package com.school.school.security.Entity;

import com.school.school.security.Entity.Enum.TypeDeRole;
import jakarta.persistence.*;
import lombok.*;

import static com.school.school.security.Entity.Enum.TypeDeRole.ETUDIANT;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_user_security")
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;


}
