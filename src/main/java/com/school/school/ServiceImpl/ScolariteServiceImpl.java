package com.school.school.ServiceImpl;

import com.school.school.Models.Message;
import com.school.school.Models.Scolarite;
import com.school.school.Repository.ScolariteRepository;
import com.school.school.Service.ScolariteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScolariteServiceImpl implements ScolariteService {
    @Autowired
    private ScolariteRepository scolariteRepository;

    @Override
    public List<Scolarite> listeScolarite() {
        return scolariteRepository.findAll();
    }

    @Override
    public Message createScolarite(Scolarite scolarite) {
        try {
            scolariteRepository.save(scolarite);
            return new Message(1, "reussi", scolarite);
        } catch (Exception e) {
            return new Message(0, "echec", null);
        }
    }

    @Override
    public Message deleteScolarite(Long id) {

        try {
            Optional<Scolarite> scolarite = scolariteRepository.findById(id);
            scolariteRepository.deleteById(id);
            return new Message(1, "supp(" + scolarite.get().getId() + ") supprimé!! ", id);
        } catch (Exception e) {
            return new Message(0, "Echec", null);
        }
    }

    @Override
    public Message searchScolariteByID(Long id) {
        try {
            scolariteRepository.findById(id);
            return new Message(1, "recherche reussi", scolariteRepository.findById(id));
        } catch (Exception e) {
            return new Message(0, "Echec merci de verifier l'ID " + id + " !!!", null);
        }
    }

    @Override
    public Message updateScolarite(Long id, Scolarite scolarite) {
        try {
            Optional<Scolarite> scolarite1 = scolariteRepository.findById(id);
            scolarite1.get().setAnneeScolaire(scolarite.getAnneeScolaire());
            scolariteRepository.save(scolarite1.get());
            return new Message(1,"Mis en jour",scolarite1.get());
        }catch (Exception e){
            return new Message(0,"Echec",null);
        }
    }

    @Override
    public Message countNumberScolarite() {
        try {
            long test =   scolariteRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}