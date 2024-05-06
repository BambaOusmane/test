package com.school.school.ServiceImpl;

import com.school.school.Models.Filiere;
import com.school.school.Models.Message;
import com.school.school.Repository.FiliereRepository;
import com.school.school.Service.FiliereService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FiliereServiceImpl implements FiliereService {
    @Autowired
    private FiliereRepository filiereRepository;

    @Override
    public List<Filiere> listeFiliere() {
     return filiereRepository.findAll();
    }

    @Override
    public Message createFiliere(Filiere filiere) {
        try {
            filiereRepository.save(filiere);
            return new Message(1,"Enregistrement avec Succes",null);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }

    @Override
    public Message deleteFiliere(Long id) {

       try {
          Optional<Filiere> filiere= filiereRepository.findById(id);
           filiereRepository.deleteById(id);
           return new Message(1,"supp("+filiere.get().getNom()+") supprimé!! ",id);
       }catch (Exception e){
           return new Message(0,"Echec",null);
       }
    }

    @Override
    public Message updateFiliere(Long id, Filiere filiere) {
        try {
            Optional<Filiere> filiere1= filiereRepository.findById(id);
            filiere1.get().setNom(filiere.getNom());
            filiereRepository.save(filiere1.get());
            return new Message(1,"Mis en jour",filiere1.get());
        }catch (Exception e){
            return new Message(0,"Echec",null);
        }
    }

    @Override
    public Message getById(Long id) {
        try {
            filiereRepository.findById(id);
            return new Message(1,"recherche reussi",filiereRepository.findById(id));

        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);

        }
    }

    @Override
    public Message countNumberFiliere() {
        return null;
    }
}
