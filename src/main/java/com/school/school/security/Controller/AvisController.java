package com.school.school.security.Controller;

import com.school.school.security.Entity.Avis;
import com.school.school.security.Service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("avis")
@RestController
@AllArgsConstructor
public class AvisController {
    private final AvisService avisService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creer(@RequestBody Avis avis){
        this.avisService.creer(avis);

    }
    @GetMapping
    public List<Avis> liste(){
       return this.avisService.liste();

    }
}
