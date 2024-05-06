package com.school.school.Repository;

import com.school.school.Models.Role;
import com.school.school.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName (String roleName) ;
    long count();
}
