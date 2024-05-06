package com.school.school.Controllers;

import com.school.school.Models.Message;
import com.school.school.Models.Options;
import com.school.school.Service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apioption")
@CrossOrigin("*")
public class OptionsController {
    @Autowired
    private OptionsService optionsService;
    @GetMapping("/get")
    public List<Options> getOption(){
        return optionsService.listeOption();
    }
    @PostMapping("/post")
    public Message postOption(@RequestBody Options options){
        return optionsService.creationOption(options);
    }
    @DeleteMapping("/delete/{id}")
    public Message deleteOption(@PathVariable Long id){
        return optionsService.deleteOption(id);
    }
    @GetMapping("/getbyid/{id}")
    public Message getById(@PathVariable Long id){
        return optionsService.getById(id);
    }
    @PutMapping("/update/{id}")
    public Message updateOption(@PathVariable Long id,@RequestBody Options options){
        return optionsService.updateOption(id,options);
    }
    @GetMapping("/count")
    public Message count (){
        return optionsService.countNumberOption();
    }

}
