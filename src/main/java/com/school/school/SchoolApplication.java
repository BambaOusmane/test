package com.school.school;


import com.school.school.security.Entity.RoleUser;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Repository.UtilsateurRepository;
import com.school.school.security.Entity.Enum.TypeDeRole;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

//(exclude={SecurityAutoConfiguration.class})
@EnableScheduling
@SpringBootApplication
@AllArgsConstructor
public class SchoolApplication implements CommandLineRunner {
    UtilsateurRepository utilsateurRepository;
    PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

//    @Override
//    public void run(String... args) throws Exception {
//        Utilisateur admin = Utilisateur.builder()
//                .actif(true)
//                .nom("admin")
//                .mdp(passwordEncoder.encode("admin"))
//                .email("ob7352480@gmail.com")
//                .role(
//                        RoleUser.builder()
//                                .libelle(TypeDeRole.ADMINISTRATEUR)
//                                .build()
//                )
//                .build();
//        admin = this.utilsateurRepository.findByEmail("ob7352480@gmail.com")
//                .orElse(admin);
//        this.utilsateurRepository.save(admin);
//
//
//        Utilisateur user = Utilisateur.builder()
//                .actif(true)
//                .nom("user")
//                .mdp(passwordEncoder.encode("user"))
//                .email("user@gmail.com")
//                .role(
//                        RoleUser.builder()
//                                .libelle(TypeDeRole.UTILISATEUR)
//                                .build()
//                )
//                .build();
//        user = this.utilsateurRepository.findByEmail("user@gmail.com")
//                .orElse(user);
//        this.utilsateurRepository.save(user);
//
//        Utilisateur manager = Utilisateur.builder()
//                .actif(true)
//                .nom("manager")
//                .mdp(passwordEncoder.encode("manager"))
//                .email("manager@gmail.com")
//                .role(
//                        RoleUser.builder()
//                                .libelle(TypeDeRole.MANAGER)
//                                .build()
//                )
//                .build();
//        manager = this.utilsateurRepository.findByEmail("manager@gmail.com")
//                .orElse(manager);
//        this.utilsateurRepository.save(manager);
//    }
}
