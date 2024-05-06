package com.school.school.security.Repository;

import com.school.school.security.Entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilsateurRepository extends CrudRepository<Utilisateur,Integer> {
  Optional <Utilisateur> findByEmail(String email);
}
