package kr.ac.hansung.cse.hellospringsecurity.repository;

import kr.ac.hansung.cse.hellospringsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}