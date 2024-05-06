 package com.school.school.ServiceImpl;

 import java.util.List;
 import java.util.Optional;

 import com.school.school.Models.Role;
 import com.school.school.Repository.RoleRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.dao.DataIntegrityViolationException;
 import org.springframework.stereotype.Service;

 import com.school.school.Models.Message;
 import com.school.school.Models.User;
 import com.school.school.Repository.UserRepository;
 import com.school.school.Service.UserService;
 import org.springframework.web.bind.annotation.CrossOrigin;

 @Service
 @CrossOrigin("*")
// @Transactional
 public class UserServiceImpl implements UserService {
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private RoleRepository roleRepository;

     @Override
     public Message createUser(User user) {
         int rand = (int) ((Math.random() * (100 - 10)) + 10);
         String noms = user.getPrenom().replace(" ","");
             String logins = noms+rand;

         try {
             user.setLogin(logins.toLowerCase());
//             User userDansBDD=   this.userRepository.findByEmail(user.getEmail());
//             if (userDansBDD ==null){
               User createUsers = userRepository.save(user);
//             }
//             else {
//                 return new Message(0, "Email existe deja dans la base",null);
//             }
//         user.setRole(new ArrayList<>());
//            user.setRole(user.getRole().add());
             return new Message(1, "Enregistrement avec Succes", createUsers);
         }catch (DataIntegrityViolationException e){
             return new Message(1, "Email existe",user.getEmail());

         }
         catch (Exception e) {

             return new Message(0, " ECHEC", null);
         }

//         try{
//             user.setLogin(logins.toLowerCase());
//             userRepository.save(user);
//         }catch (Exception e){
//             return new Message(0, "error", null);
//         }

//         return new Message(1, "message", null);

     }

     @Override
     public Message deleteUser(Long id) {
         Optional<User> user = userRepository.findById(id);
         try {
             userRepository.deleteById(id);
             return new Message(1, "supp(" + user.get().getNom() + ") supprimé!! ", id);
         } catch (Exception e) {
             return new Message(0, "ECHEC", null);
         }
     }

     @Override
     public List<User> getUser() {
         return userRepository.findAll();

     }

//     public List<Produit> listeProduit(){
//         List<Produit> liste = new ArrayList<>();
//         liste=produitRepository.findAll();
//         return liste;
//     }

     @Override
     public Message searchUserById(Long id) {

         try {
             Optional<User> user = userRepository.findById(id);
             return new Message(1, "recherche reussi", user);
         } catch (Exception e) {
             return new Message(0, "Echec merci de verifier l'ID " + id + " !!!", null);
         }
     }

     @Override
     public Message countNumberUser() {
         try {
             long test = userRepository.count();
             return new Message(1, "Le nombre total des utilisateur ", test);
         } catch (Exception e) {
             return new Message(0, "ECHEC", null);
         }

     }

     @Override
     public Message statusUser(Long id, User user) {
         try {
             Optional<User> user1 = userRepository.findById(id);
             user1.get().setStatus(user.getStatus());
             userRepository.save(user1.get());
             return new Message(1, "La mis en jour du  (" + user1.get().getNom() + ") est fait avec succes !!", user1.get());
         } catch (Exception e) {
             return new Message(0, "Echec merci de verifier l'ID " + id + " !!!", null);

         }
     }

     @Override
     public Message add_role_user(String login, String roleName) {
         try {
             User user1 = userRepository.findByLogin(login);
             Role role1 = roleRepository.findByRoleName(roleName);
             System.out.println(user1.getLogin());
             System.out.println(role1.getRoleName());
//             if (role1.isPresent() && user1.isPresent()){
         user1.getRole().add(role1);
                 userRepository.save(user1);
                 return new Message(1,"test",user1);
//             }
         } catch (Exception e){
             return new Message(0,"echec",null);
         }

     }
     @Override
     public Message updateUser(User user, Long id) {
         try {
             User U = userRepository.findUserById(id);
             U.setAdresse(user.getAdresse());
             U.setEmail(user.getEmail());
             U.setLogin(user.getLogin());
             U.setNom(user.getNom());
             U.setMatricule(user.getMatricule());
             U.setDateInscription(user.getDateInscription());
             U.setPassword(user.getPassword());
             U.setPhone(user.getPhone());
             U.setRole(user.getRole());
             U.setPrenom(user.getPrenom());
             userRepository.save(U);
       return new Message(1,"mis en jours efffectue avec success",U);
         }catch (Exception e){
             return new Message(0,"Echec merci de verifier l'ID "+id+" !!!",null);
         }

     }
 }

