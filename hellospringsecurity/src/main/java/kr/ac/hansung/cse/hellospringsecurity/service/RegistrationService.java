package kr.ac.hansung.cse.hellospringsecurity.service;

import kr.ac.hansung.cse.hellospringsecurity.entity.Role;
import kr.ac.hansung.cse.hellospringsecurity.entity.User;

import java.util.List;

public interface RegistrationService {
    User createUser(User user, List<Role> userRoles);

    //중복 체크 하기위함
    boolean checkEmailExists(String email);

    Role findByRolename(String rolename);
}
