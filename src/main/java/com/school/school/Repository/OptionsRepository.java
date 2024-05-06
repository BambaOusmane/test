package com.school.school.Repository;

import com.school.school.Models.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<Options,Long> {
    long count();
}
