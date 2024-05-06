package com.school.school.security.Controller;

import com.school.school.security.Config.AuthentificationDTO;
import com.school.school.security.Config.JwtService;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class CompteController {
    private UtilisateurService utlisateurService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping("inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){
        this.utlisateurService.inscription(utilisateur);
    }
    @PostMapping("activation")
    public void activation (@RequestBody Map<String,String> activation){
        this.utlisateurService.activation(activation);
    }

    @PostMapping("refresh-token")
    public @ResponseBody Map<String , String> refreshToken (@RequestBody Map<String,String> refreshTokenRequest){
       return this.jwtService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("modifier-mot-de-passe")
    public void modifieMotDePasse (@RequestBody Map<String,String> activation){
        this.utlisateurService.modifieMotDePasse(activation);
    }
    @PostMapping("nouveau-mot-de-passe")
    public void nouveaueMotDePasse (@RequestBody Map<String,String> activation){
        this.utlisateurService.nouveauMotDePasse(activation);
    }
    @PostMapping("deconnexion")
    public void deconnexion (){
        this.jwtService.deconnexion();
    }

    @PostMapping("connexion")
    public Map<String , String> connexion (@RequestBody AuthentificationDTO authentificationDTO){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO
                        .password())
        );
        if (authenticate.isAuthenticated()){
         return this.jwtService.generete(authentificationDTO.username());
        }
//        log.info("Resultat {}",authenticate.isAuthenticated());
        return null;
    }
}
