package com.school.school.Repository;

import com.school.school.Models.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRepository extends JpaRepository<Prof,Long> {
    long count();
}
