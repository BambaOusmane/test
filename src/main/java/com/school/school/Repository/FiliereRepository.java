package com.school.school.Repository;

import com.school.school.Models.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FiliereRepository extends JpaRepository<Filiere,Long> {
    long count();
}
