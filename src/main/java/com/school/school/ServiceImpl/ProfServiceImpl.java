package com.school.school.ServiceImpl;

import com.school.school.Models.Message;
import com.school.school.Models.Prof;
import com.school.school.Repository.ProfRepository;
import com.school.school.Service.ProfService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class         ProfServiceImpl implements ProfService {
    @Autowired
    private ProfRepository profRepository;
    @Override
    public List<Prof> listeProf() {
        return profRepository.findAll();
    }

    @Override
    public Message createProf(Prof prof) {
        try {
            profRepository.save(prof);
            return new Message(1,"Enregistrement avec Succes",prof);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }

    @Override
    public Message deleteProf(Long id) {
        try {
            Optional<Prof> prof =profRepository.findById(id);
            profRepository.deleteById(id);
            return new Message(1,"supp("+prof.get().getNom()+") supprimé!! ",id);
        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);
        }
    }

    @Override
    public Message getById(Long id) {
        try {
            profRepository.findById(id);

            return new Message(1,"recherche effectue",profRepository.findById(id));
        }catch (Exception e){
            return new Message(0,"Echec de recherche",null);
        }

    }

    @Override
    public Message updateProf(Long id, Prof prof) {
        Optional<Prof> prof1=profRepository.findById(id);

//        System.out.println("prof1"+prof1.get().getId());
        if (prof1.isPresent()){
//
            try {

                prof1.get().setGrade(prof.getGrade());
                prof1.get().setAdresse(prof.getAdresse());
                prof1.get().setEmail(prof.getEmail());
                prof1.get().setNom(prof.getNom());
                prof1.get().setLogin(prof.getLogin());
//                prof1.get().setDateInscription(prof.getDateInscription());
                prof1.get().setMatricule(prof.getMatricule());
                prof1.get().setPassword(prof.getPassword());
                prof1.get().setRole(prof.getRole());
                prof1.get().setPhone(prof.getPhone());
                prof1.get().setPrenom(prof.getPrenom());
                profRepository.save(prof1.get());

            }catch (Exception e){
                System.out.println(e);
                return new Message(0,"Mis en jour a echoue !",null);
            }
        }else {
            return new Message(0,"Cet"+id+"n'existe pas",null);
        }
        return new Message(1,"Mis en jour reussi !",prof1.get());

    }

    @Override
    public Message countNumberProf() {
        try {
            long test =   profRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}
