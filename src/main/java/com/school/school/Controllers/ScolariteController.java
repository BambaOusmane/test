package com.school.school.Controllers;

import com.school.school.Models.Message;
import com.school.school.Models.Role;
import com.school.school.Models.Scolarite;
import com.school.school.Service.ScolariteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiscolarite")
@CrossOrigin("*")
public class ScolariteController {
    @Autowired
    private ScolariteService scolariteService;
    @GetMapping("/get")
    public List<Scolarite> getScolarite(){
        return scolariteService.listeScolarite();
    }
    @GetMapping("/getbyid/{id}")

    public Message getByIdScolarite(@PathVariable Long id){
        return scolariteService.searchScolariteByID(id);
    }
    @PostMapping("/pot")
    public Message postScolarite(@RequestBody Scolarite scolarite){
        return scolariteService.createScolarite(scolarite);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteScolarite(@PathVariable Long id){
        return scolariteService.deleteScolarite(id);
    }
    @PutMapping("/update/{id}")
    public Message updateScolarite(@PathVariable Long id, @RequestBody Scolarite scolarite){
        return scolariteService.updateScolarite(id,scolarite);
    }
    @GetMapping("/count")
    public Message count (){
        return scolariteService.countNumberScolarite();
    }
}
