package com.school.school.Controllers;

import com.school.school.Models.Message;
import com.school.school.Models.Role;
import com.school.school.Models.User;
import com.school.school.Repository.UserRepository;
import com.school.school.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/apiuser",produces = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class UserController {
@Autowired
    private  UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//public static String uploadDirectory= System.getProperty("user.dir") + "/src/main/webapp/images";
    @PostMapping(value = "/post",consumes = APPLICATION_JSON_VALUE)
    public Message addUser(@RequestBody User user)  {
//         String originalFilename = file.getOriginalFilename();
//         System.out.println("image infos");
//         Path fileNameAndPath = Paths.get(uploadDirectory,originalFilename);
//        Files.write(fileNameAndPath, file.getBytes());
//        user.setProfileImage(originalFilename);
        return userService.createUser(user);
    }

    @PutMapping("userstatus/{id}")
    public Message userStatus(@PathVariable Long id, @RequestBody User user){
        return userService.statusUser(id,user);
    }
    @GetMapping("/get")
    public List<User> listeUser (){
    return userService.getUser();}
@DeleteMapping("/delete/{id}")
    public Message deleteUser(@PathVariable Long id){
return userService.deleteUser(id);}
    @PutMapping("/update/{id}")
    public Message updateUser(@PathVariable Long id,@RequestBody User user){
        return userService.updateUser(user,id);}
@GetMapping("/getbyid/{id}")
    public Message getUserById(@PathVariable Long id){
        return userService.searchUserById(id);}
    @GetMapping("/count")
    public Message count (){
     return userService.countNumberUser();
    }
    @PostMapping("/role_user")
    public Message role_user(@RequestBody Role_User_Form roleUserForm ){
//        System.out.println(roleUserForm.getLogin());
        return userService.add_role_user(roleUserForm.getLogin(),roleUserForm.getRoleName());
    }
//    @PostMapping("/image")
//    public ResponseEntity<User> createImage(@RequestParam String name, @RequestPart("image") MultipartFile file) throws IOException {
//User user=User.builder().nom(name).displayPicture(file.getBytes()).build();
//userRepository.save(user);
//user.setDisplayPicture(null);
//return ResponseEntity.ok(user);
//    }

}
@Data
class Role_User_Form{
    private String login;
    private String roleName;
}

