package com.school.school.security.Service;

import com.school.school.security.Entity.Avis;
import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Repository.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisService {
    private  final AvisRepository avisRepository;

    public void creer(Avis avis){
     Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     avis.setUtilisateur(utilisateur);
        this.avisRepository.save(avis);
    }

    public List<Avis> liste(){
        return (List<Avis>) this.avisRepository.findAll();
    }

}
