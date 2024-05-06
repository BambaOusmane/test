package com.school.school.Controllers;

import com.school.school.Models.Filiere;
import com.school.school.Models.Message;
import com.school.school.Service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apifiliere")
@CrossOrigin("*")
public class FiliereController {
    @Autowired
    private FiliereService filiereService;
    @GetMapping("/get")
    public List<Filiere> getFiliere(){
        return filiereService.listeFiliere();
    }
    @PostMapping("/post")
    public Message postFiliere(@RequestBody Filiere filiere){
        return filiereService.createFiliere(filiere);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteFiliere(@PathVariable Long id){
        return filiereService.deleteFiliere(id);
    }
    @PutMapping("/update/{id}")
    public Message updateFiliere(@PathVariable Long id , @RequestBody Filiere filiere){
        return filiereService.updateFiliere(id,filiere);
    }
    @GetMapping("/getbyid/{id}")
    public Message searchByIDFiliere(@PathVariable Long id){
        return filiereService.getById(id);
    }
    @GetMapping("/count")
    public Message count (){
        return filiereService.countNumberFiliere();
    }
}
