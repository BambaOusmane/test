package com.school.school.Service;

import com.school.school.Models.Message;
import com.school.school.Models.Options;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface OptionsService {
    public List<Options> listeOption();
    public Message creationOption(Options options);
    public Message deleteOption(Long id);
    public Message getById(Long id);
    public Message updateOption(Long id,Options options);
    public Message countNumberOption( );

}
