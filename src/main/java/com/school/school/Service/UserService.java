package com.school.school.Service;

import java.util.List;

import com.school.school.Models.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.school.school.Models.Message;
import com.school.school.Models.User;

//@Service
//@Transactional
public interface UserService {
    // Create User
    public Message createUser(User user);

    // read User
    public List<User> getUser();

    // delete User by id
    public Message deleteUser(Long id);

    Message add_role_user(String login, String roleName);

    // update User by id
    public Message updateUser(User user, Long id);

    // search User by id
    public Message searchUserById(Long id);

    public Message countNumberUser();

    public Message statusUser(Long id, User user);
//    public Message add_role_user(User user, Role role);

}
