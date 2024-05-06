package com.school.school.ServiceImpl;

import com.school.school.Models.Etudiant;
import com.school.school.Models.Message;
import com.school.school.Repository.EtudiantRepository;
import com.school.school.Service.EtudiantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Message CreateEtudiant(Etudiant etudiant) {
     try {
         etudiantRepository.save(etudiant);
      return new Message(1,"Enregistrement avec Succes",etudiant);
     }catch (Exception e){
       return new Message(0,"echec",null);
     }
    }

    @Override
    public List<Etudiant> listeEtudiant() {
      return   etudiantRepository.findAll();
    }

    @Override
    public Message deleteEtudiant(Long id) {

       try {
           Optional<Etudiant> etudiant = etudiantRepository.findById(id);
           etudiantRepository.deleteById(id);
           return new Message(1,"supp("+etudiant.get().getNom()+") supprimé!! ",id);
       }catch (Exception e){
           return new Message(0,"Echec",null);
       }
    }

    @Override
    public Message updateEtudiant(Etudiant etudiant, Long id) {
        try {
            Optional<Etudiant> etudiant1=etudiantRepository.findById(id);
            etudiant1.get().setAdresse(etudiant.getAdresse());
            etudiant1.get().setEmail(etudiant.getEmail());
            etudiant1.get().setNom(etudiant.getNom());
            etudiant1.get().setLogin(etudiant.getLogin());
            etudiant1.get().setMatricule(etudiant.getMatricule());
            etudiant1.get().setPhone(etudiant.getPhone());
            etudiant1.get().setRole(etudiant.getRole());
//            etudiant1.get().setDateInscription(etudiant.getDateInscription());
            etudiant1.get().setPassword(etudiant.getPassword());
            etudiant1.get().setPrenom(etudiant.getPrenom());
            etudiantRepository.save(etudiant1.get());
       return new Message(1,"Mis en jour",etudiant1.get());
        }catch (Exception e){
            return new Message(0,"Echec",null);
        }
    }

    @Override
    public Message searchById(Long id) {
        try {
            etudiantRepository.findById(id).get();
            return new Message(1,"recherche reussi",etudiantRepository.findById(id));
        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);
        }

    }

    @Override
    public Message countNumberExamen() {
        try {
            long test =   etudiantRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}