package com.school.school.Service;

import com.school.school.Models.Message;
import com.school.school.Models.Prof;
import com.school.school.Models.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface RoleService {
    public List<Role> listeRole();
    public Message createRole(Role role);
    public Message deleteRole(Long id);
    public Message searchRoleByID(Long id);
    public Message updateRole(Long id,Role role);
    public Message countNumberRole( );
}
