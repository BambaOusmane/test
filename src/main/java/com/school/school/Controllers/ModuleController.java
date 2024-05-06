package com.school.school.Controllers;

import com.school.school.Models.Message;
import com.school.school.Models.Module;
import com.school.school.Service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apimodule")
@CrossOrigin("*")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;
    @GetMapping("/get")
    public List getModule(){
        return moduleService.listeModule();
    }
    @PostMapping("/post")
    public Message postModule(@RequestBody Module module){
        return moduleService.ajoutModule(module);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteModule(@PathVariable Long id){
        return moduleService.DeleteModule(id);
    }
    @GetMapping("/getbyid/{id}")
    public Message getById(@PathVariable Long id){
        return moduleService.getById(id);
    }
    @PutMapping("/update/{id}")
    public Message updateModule(@PathVariable Long id, @RequestBody Module module){
        return moduleService.updateModule(id,module);
    }
    @GetMapping("/count")
    public Message count (){
        return moduleService.countNumberModule();
    }
}
