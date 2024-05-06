package com.school.school.Controllers;

import com.school.school.Models.Message;
import com.school.school.Models.Role;
import com.school.school.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apirole")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/get")
    public List<Role> getRole(){
        return roleService.listeRole();
    }
    @GetMapping("/getbyid/{id}")

    public Message getByIdRole(@PathVariable Long id){
        return roleService.searchRoleByID(id);
    }
    @PostMapping("/post")
    public Message postRole(@RequestBody Role role){
        return roleService.createRole(role);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteRole(@PathVariable Long id){
        return roleService.deleteRole(id);
    }
    @PutMapping("/update/{id}")
    public Message updateRole(@PathVariable Long id, @RequestBody Role role){
        return roleService.updateRole(id,role);
    }
    @GetMapping("/count")
    public Message count (){
        return roleService.countNumberRole();
    }
}
