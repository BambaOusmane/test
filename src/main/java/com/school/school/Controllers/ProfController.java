package com.school.school.Controllers;

import com.school.school.Models.Message;
import com.school.school.Models.Prof;
import com.school.school.Service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apiprof")
@CrossOrigin("*")
public class ProfController {
    @Autowired
    private ProfService profService;
    @GetMapping("/get")
    public List<Prof> getProf(){
        return profService.listeProf();
    }
    @PostMapping("/post")
    public Message postProf(Prof prof){
       return profService.createProf(prof);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteProf(@PathVariable Long id){
        return profService.deleteProf(id);
    }
    @GetMapping("/getbyid/{id}")
    public Message getByIdProf(@PathVariable Long id){
        return profService.getById(id);
    }
    @PutMapping("/update/{id}")
    public Message updateProf(@PathVariable Long id,@RequestBody Prof prof){
        return profService.updateProf(id,prof);
    }
    @GetMapping("/count")
    public Message count (){
        return profService.countNumberProf();
    }
}
