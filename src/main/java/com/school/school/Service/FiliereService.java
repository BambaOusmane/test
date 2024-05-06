package com.school.school.Service;

import com.school.school.Models.Filiere;
import com.school.school.Models.Message;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface FiliereService {
    public List<Filiere> listeFiliere();
    public Message createFiliere(Filiere filiere);
    public Message deleteFiliere(Long id );
    public Message updateFiliere(Long id,Filiere filiere);
    public Message getById(Long id);
    public Message countNumberFiliere( );

}
