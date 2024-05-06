package com.school.school.Repository;

import com.school.school.Models.Scolarite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScolariteRepository extends JpaRepository<Scolarite,Long> {
    long count();
}
