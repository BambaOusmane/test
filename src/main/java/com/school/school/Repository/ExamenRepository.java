package com.school.school.Repository;

import com.school.school.Models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {
    long count();
}

