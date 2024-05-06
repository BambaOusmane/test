package com.school.school.security.Controller;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("utlisateur")
@RestController
public class UtilisateurController {
    UtilisateurService utilisateurService;

    @PreAuthorize("hasAuthority('ADMINISTRATEUR_READ')")
    @GetMapping
    public List<Utilisateur> liste(){
        return this.utilisateurService.liste();

    }
}
