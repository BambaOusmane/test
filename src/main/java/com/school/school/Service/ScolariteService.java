package com.school.school.Service;

import com.school.school.Models.Message;
import com.school.school.Models.Role;
import com.school.school.Models.Scolarite;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface ScolariteService {
    public List<Scolarite> listeScolarite();
    public Message createScolarite(Scolarite scolarite);
    public Message deleteScolarite(Long id);

    Message searchScolariteByID(Long id);

    public Message updateScolarite(Long id, Scolarite scolarite);
    public Message countNumberScolarite( );
}
