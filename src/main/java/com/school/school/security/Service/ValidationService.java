package com.school.school.security.Service;

import com.school.school.security.Entity.Utilisateur;
import com.school.school.security.Entity.Validation;
import com.school.school.security.Repository.ValidationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class ValidationService {
   private ValidationRepository validationRepository;
   private NotificationService notificationService;

   public void enregistrer(Utilisateur utilisateur){
       Validation validation = new Validation();
       validation.setUtilisateur(utilisateur);
       Instant creation = Instant.now();

       Instant expiration = creation.plus(10, MINUTES);
       validation.setExpiration(expiration);

       Random random = new Random();
       int randomInteger = random.nextInt(999999);
       String code = String.format("%06d", randomInteger);
       validation.setCode(code);

       this.validationRepository.save(validation);
       this.notificationService.envoyer(validation);
   }

   public Validation lireEnfonctionDuCode (String code){
   return     this.validationRepository.findByCode(code).orElseThrow(()-> new RuntimeException("Votre code est invalide"));

   }
//   @Scheduled(cron = "*/30 * * * * *")
    public void nettoyerTable (){
       final Instant now = Instant.now();
       log.info("Suppresssion des token a {}",now);
    this.validationRepository.deleteAllByExpirationBefore(Instant.now());
   }
}
