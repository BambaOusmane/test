package com.school.school.Service;

import com.school.school.Models.Examen;
import com.school.school.Models.Message;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface ExamenService {
    public List<Examen> listeExamen();
    public Message createExamen(Examen examen);
    public Message deleteExamen(Long id);
    public Message updateExamen(Long id ,Examen examen);
    public Message getByID(Long id);
    public Message countNumberExamen( );

}
