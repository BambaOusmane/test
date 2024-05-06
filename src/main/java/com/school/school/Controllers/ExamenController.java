package com.school.school.Controllers;

import com.school.school.Models.Examen;
import com.school.school.Models.Message;
import com.school.school.Service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiexamen")
@CrossOrigin("*")
public class ExamenController {
    @Autowired
    private ExamenService examenService;
    @GetMapping("/get")
    public List<Examen> getExamen(){
        return examenService.listeExamen();
    }
    @PostMapping("/post")
    public Message ajoutExamen(@RequestBody Examen examen){
      return   examenService.createExamen(examen);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteExamen(@PathVariable Long id){
        return examenService.deleteExamen(id);
    }
    @PutMapping("/update/{id}")
    public Message updateExamen(@PathVariable Long id ,@RequestBody Examen examen){
        return examenService.updateExamen(id,examen);
    }
    @GetMapping("/getbyid/{id}")
    public Message GetById(@PathVariable Long id){
        return examenService.getByID(id);
    }
    @GetMapping("/count")
    public Message count (){
        return examenService.countNumberExamen();
    }

}
