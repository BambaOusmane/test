package com.school.school.Service;

import com.school.school.Models.Message;
import com.school.school.Models.Module;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface ModuleService {
    public List<Module> listeModule();
    public Message ajoutModule(Module module);
    public Message DeleteModule(Long id);
    public Message getById(Long id);
    public Message updateModule(Long id , Module module);
    public Message countNumberModule( );




}
