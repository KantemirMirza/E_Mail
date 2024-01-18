package com.kani.e_mail.repository;


import com.kani.e_mail.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUserEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
