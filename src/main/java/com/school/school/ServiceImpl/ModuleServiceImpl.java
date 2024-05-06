package com.school.school.ServiceImpl;

import com.school.school.Models.Message;
import com.school.school.Models.Module;
import com.school.school.Repository.ModuleRepository;
import com.school.school.Service.ModuleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List listeModule() {
        return moduleRepository.findAll();
    }

    @Override
    public Message ajoutModule(Module module) {
        try {
             moduleRepository.save(module);
             return new Message(1,"Enregistrement avec Succes",module);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }

    @Override
    public Message DeleteModule(Long id) {
        try {
            Optional<Module> module= moduleRepository.findById(id);
            moduleRepository.deleteById(id);
            return new Message(1,"supp("+module.get().getNom()+") supprimé!! ",id);
        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);

        }
    }

    @Override
    public Message getById(Long id) {
        try {
            moduleRepository.findById(id);
            return new Message(1,"recherche effectue",moduleRepository.findById(id));
        }catch (Exception e){
            return new Message(0,"Echec de recherche",null);
        }
    }

    @Override

    public Message updateModule(Long id, Module module) {
        try {
            Optional<Module> module1=moduleRepository.findById(id);
            module1.get().setNom(module.getNom());
            module1.get().setVolume(module.getVolume());
            moduleRepository.save(module1.get());
            return new Message(1,"Mis en jour reussi !",module1.get());
        }catch (Exception e){
            return new Message(0,"Mis en jour echoue !",null);
        }
    }

    @Override
    public Message countNumberModule() {
        try {
            long test =   moduleRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}
