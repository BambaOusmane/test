package com.school.school.security.Service;

import com.school.school.security.Entity.RoleUser;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Entity.Validation;
import com.school.school.security.Repository.UtilsateurRepository;
import com.school.school.security.Entity.Enum.TypeDeRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {

    private UtilsateurRepository utilsateurRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ValidationService validationService;

    public void inscription (Utilisateur utilisateur){
        if (!utilisateur.getEmail().contains("@")){
            throw new RuntimeException("Votre mail est invalide");
        }
        if (!utilisateur.getEmail().contains(".")){
            throw new RuntimeException("Votre mail est invalide");
        }
        Optional <Utilisateur> utilisateurOptional = this.utilsateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre mail est deja utilise");
        }
      String mdpCrypte =  this.bCryptPasswordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(mdpCrypte);
        utilisateur = this.utilsateurRepository.save(utilisateur);
        this.validationService.enregistrer(utilisateur);
    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireEnfonctionDuCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code a repiré utilise");
        }
      Utilisateur utilisateurActiver =  this.utilsateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(()-> new RuntimeException("Utilisateur Inconnu"));
      utilisateurActiver.setActif(true);
      this.utilsateurRepository.save(utilisateurActiver);
    }

    @Override
    public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
      return this.utilsateurRepository.findByEmail(username)
              .orElseThrow(()-> new UsernameNotFoundException("Aucun utlisateur ne correspond a cet identifian "));

    }

    public void modifieMotDePasse(Map<String, String> parametres) {
        Utilisateur utilisateur = this.loadUserByUsername(parametres.get("email"));
        this.validationService.enregistrer(utilisateur);
    }

    public void nouveauMotDePasse(Map<String, String> parametres) {
        Utilisateur utilisateur = this.loadUserByUsername(parametres.get("email"));
        final Validation validation = validationService.lireEnfonctionDuCode(parametres.get("code"));
        if (validation.getUtilisateur().equals(utilisateur.getEmail())){
            String mdpCrypte =  this.bCryptPasswordEncoder.encode(parametres.get("password"));
            utilisateur.setMdp(mdpCrypte);
            this.utilsateurRepository.save(utilisateur);
        }

    }

    public List<Utilisateur> liste() {
        final Iterable<Utilisateur> utilisateurIterable = this.utilsateurRepository.findAll();
        List<Utilisateur> utilisateurs = new ArrayList<>();
         for (Utilisateur utilisateur: utilisateurIterable){
             utilisateurs.add(utilisateur);
         }
         return utilisateurs;
    }
}
