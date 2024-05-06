package com.school.school.security.Service;

import com.school.school.security.Entity.Validation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void envoyer (Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@chillo.tech");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("votre code d'activation ");

        String texte =  String.format("Bonjour %s,\n Votre d'activation est %s.\n A biemtot",
                validation.getUtilisateur().getNom(), validation.getCode());
        message.setText(texte);
        javaMailSender.send(message);


}

}


