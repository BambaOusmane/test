package com.school.school.ServiceImpl;

import com.school.school.Models.Message;
import com.school.school.Models.Options;
import com.school.school.Repository.OptionsRepository;
import com.school.school.Service.OptionsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class OptionsServiceImpl implements OptionsService {
    @Autowired
    private OptionsRepository optionsRepository;

    @Override
    public List<Options> listeOption() {
        return optionsRepository.findAll();
    }

    @Override
    public Message creationOption(Options options) {
        try {
            optionsRepository.save(options);
            return new Message(1,"Enregistrement avec Succes",options);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }

    @Override
    public Message deleteOption(Long id) {
        try {
            Optional<Options> options= optionsRepository.findById(id);
            optionsRepository.deleteById(id);
            return new Message(1,"supp("+options.get().getNom()+") supprimé!! ",id);
        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);
        }
    }

    @Override
    public Message getById(Long id) {
        try {
            optionsRepository.findById(id);
            return new Message(1,"recherche effectue",optionsRepository.findById(id));
        }catch (Exception e){
            return new Message(0,"Echec de recherche",null);
        }
    }

    @Override
    public Message updateOption(Long id, Options options) {
        try {
            Optional<Options> options1=optionsRepository.findById(id);
            options1.get().setNom(options.getNom());
            options1.get().setNiveau(options.getNiveau());
            optionsRepository.save(options1.get());
            return new Message(1, "Mis en jour reussi !", options1.get());
        } catch (Exception e) {
            return new Message(0, "Mis en jour echoue !", null);
        }
    }

    @Override
    public Message countNumberOption() {
     try {
            long test =   optionsRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}
