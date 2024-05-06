package com.school.school.security.Repository;

import com.school.school.security.Entity.Avis;
import com.school.school.security.Entity.RoleUser;
import org.springframework.data.repository.CrudRepository;

public interface RoleUserRepository extends CrudRepository<RoleUser, Integer > {
//    void save(RoleUser roleUser);
}
