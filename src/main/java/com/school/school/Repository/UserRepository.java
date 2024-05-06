package com.school.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.school.Models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
//    @Query("SELECT COUNT(u) FROM User u")
//    Long countUsers();
//    long countByUsername(String nom);
   User findByLogin (String login) ;
    User findUserById (Long id) ;

    User findByEmail (String email) ;
    long count();
    List<User> findAll();
}