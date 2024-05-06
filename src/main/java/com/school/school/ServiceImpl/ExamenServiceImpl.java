package com.school.school.ServiceImpl;

import com.school.school.Models.Examen;
import com.school.school.Models.Message;
import com.school.school.Repository.ExamenRepository;
import com.school.school.Service.ExamenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExamenServiceImpl implements ExamenService {
    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public List<Examen> listeExamen() {
        return examenRepository.findAll();
    }

    @Override
    public Message createExamen(Examen examen) {
        try {
           examenRepository.save(examen);
            return new Message(1,"Enregistrement avec Succes",examen);

        }catch (Exception e){
            return new Message(0,"echec",null);
        }
    }

    @Override
    public Message deleteExamen(Long id) {

       try {
           Optional<Examen> examen= examenRepository.findById(id);
           examenRepository.deleteById(id);
           return new Message(1,"supp("+examen.get().getNom()+") supprimé!! ",id);

       }catch (Exception e){
           return new Message(0,"Echec",null);

       }
    }

    @Override
    public Message updateExamen(Long id, Examen examen) {
        try {
            Optional<Examen> examen1=examenRepository.findById(id);
            examen1.get().setNom(examen.getNom());
            examen1.get().setDateExamen(examen.getDateExamen());
            examenRepository.save(examen);
            return new Message(1,"Mis en jour",examen1.get());
        }catch (Exception e){
            return new Message(0,"Echec",null);
        }
    }

    @Override
    public Message getByID(Long id) {
        try {
            examenRepository.findById(id);
            return new Message(1,"recherche reussi",examenRepository.findById(id));
        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);
        }
    }

    @Override
    public Message countNumberExamen() {
        try {
            long test =   examenRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}
