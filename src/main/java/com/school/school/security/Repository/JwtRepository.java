package com.school.school.security.Repository;

import com.school.school.security.Entity.Jwt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface JwtRepository extends CrudRepository<Jwt,Integer> {
//   Optional<Jwt> findByValeur(String value);

   Optional<Jwt> findByValeurAndDesactiveAndExpire(String valeur, boolean desactive, boolean expire);

   @Query("FROM Jwt j WHERE j.expire = :expire AND j.desactive = :desactive AND j.utilisateur.email = :email")
   Optional<Jwt> findUtilisateurValideToken(String email, boolean desactive, boolean expire);

   @Query("FROM Jwt j WHERE j.utilisateur.email = :email")
   Stream<Jwt> findUtilisateur(String email);

   @Query("FROM Jwt j WHERE j.refreshToken.valeur = :valeur")
   Optional<Jwt> findByRefreshToken(String valeur);


   void deleteAllByExpireAndDesactive(boolean expire, boolean desactive);


}
