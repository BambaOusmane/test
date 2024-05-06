package com.school.school.Service;

import com.school.school.Models.Etudiant;
import com.school.school.Models.Message;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface EtudiantService {
    public Message CreateEtudiant(Etudiant etudiant);
    public List<Etudiant> listeEtudiant();
    public  Message deleteEtudiant(Long id);
    public  Message updateEtudiant(Etudiant etudiant ,Long id);
    public Message searchById(Long id);
    public Message countNumberExamen( );


}
