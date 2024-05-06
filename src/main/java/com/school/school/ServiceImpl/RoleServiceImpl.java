package com.school.school.ServiceImpl;

import com.school.school.Models.Message;
import com.school.school.Models.Prof;
import com.school.school.Models.Role;
import com.school.school.Models.User;
import com.school.school.Repository.RoleRepository;
import com.school.school.Service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> listeRole() {
        return roleRepository.findAll();
    }

    @Override
    public Message createRole(Role role) {
        try {
            roleRepository.save(role);
            return new Message(1,"Enregistrement avec Succes",role);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
//    public Message createUser(User user) {
//        try {
//            userRepository.save(user);
//            return new Message(1,"Enregistrement avec Succes",user);
//        }catch(Exception e){
//            return new Message(0," ECHEC",null);
//        }
//
//    }

    @Override
    public Message deleteRole(Long id) {
        try {
            Optional<Role> role = roleRepository.findById(id);
            roleRepository.deleteById(id);
            return new Message(1,"supp("+role.get().getRoleName()+") supprimé!! ",id);
        }catch (Exception e){
            return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);
        }
    }

    @Override
    public Message searchRoleByID(Long id) {
        try {
            roleRepository.findById(id);
            return new Message(1,"recherche effectue",roleRepository.findById(id));
        }catch (Exception e){
            return new Message(0,"Echec de recherche",null);
        }

    }

    @Override
    public Message updateRole(Long id, Role role) {
        try {
            Optional<Role> role1=roleRepository.findById(id);
            role1.get().setRoleName(role.getRoleName());
            roleRepository.save(role1.get());
            return new Message(1,"Mis en jour reussi !",role1.get());
        }catch (Exception e){
            return new Message(0,"Mis en jour echoue !",null);
        }
    }

    @Override
    public Message countNumberRole() {
        try {
            long test =   roleRepository.count();
            return new Message(1,"Le nombre total des utilisateur ",test);
        }catch (Exception e){
            return new Message(0,"ECHEC",null);
        }
    }
}
