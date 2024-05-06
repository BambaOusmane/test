package com.school.school.Service;

import com.school.school.Models.Message;
import com.school.school.Models.Prof;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface ProfService {
    public List<Prof> listeProf();
    public Message createProf(Prof prof);
    public Message deleteProf(Long id);
    public Message getById(Long id);
    public Message updateProf(Long id,Prof prof);
    public Message countNumberProf( );

}
