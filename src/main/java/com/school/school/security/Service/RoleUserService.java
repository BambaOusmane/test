package com.school.school.security.Service;

import com.school.school.Models.Message;
import com.school.school.security.Entity.Avis;
import com.school.school.security.Entity.RoleUser;
import com.school.school.security.Repository.RoleUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleUserService {
    private final    RoleUserRepository roleUserRepository;

    public Message cree(RoleUser roleUser){
//     Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//     avis.setUtilisateur(utilisateur);
//        final S save = this.roleRepository.save(roleUser);
        roleUserRepository.save(roleUser);
        return new Message(1,"trewww", roleUser);
    }

//    public Iterable<RoleUser> liste(){
//        final Iterable<Avis> all = this.roleUserRepository.findAll();
//        return all;
//    }

    public List<RoleUser> getRole(){
        return (List<RoleUser>) this.roleUserRepository.findAll();
    }
}
