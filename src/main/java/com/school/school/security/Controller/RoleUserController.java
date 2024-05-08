package com.school.school.security.Controller;

import com.school.school.security.Entity.RoleUser;
import com.school.school.security.Service.RoleUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("role")
@RestController
@AllArgsConstructor
public class RoleUserController {
    private final RoleUserService roleUserService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void creer(@RequestBody RoleUser roleUser){
        this.roleUserService.cree(roleUser);

    }
    @GetMapping
    public Iterable<RoleUser> liste(){
       return this.roleUserService.getRole();

    }
}
