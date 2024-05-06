package com.school.school.Controllers;

import com.school.school.Models.Etudiant;
import com.school.school.Models.Message;
import com.school.school.Service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apietudiant")
@CrossOrigin("*")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/post")
    public void createStudent(@RequestBody Etudiant etudiant){
        etudiantService.CreateEtudiant(etudiant);
    }
    @GetMapping("/get")
    public List<Etudiant> listeStudent(){
        return etudiantService.listeEtudiant();
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteStudent(@PathVariable Long id){
        return etudiantService.deleteEtudiant(id);
    }
    @PutMapping("/update/{id}")
    public Message updateStudent(@RequestBody Etudiant etudiant, @PathVariable Long id){
        return etudiantService.updateEtudiant(etudiant , id);
    }
    @GetMapping("/getbyid/{id}")
    public Message searchByID(@PathVariable Long id){
        return etudiantService.searchById(id);
    }
    @GetMapping("/count")
    public Message count (){
        return etudiantService.countNumberExamen();
    }

}
