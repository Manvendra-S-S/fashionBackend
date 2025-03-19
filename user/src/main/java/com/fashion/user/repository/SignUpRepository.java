package com.fashion.user.repository;

import com.fashion.user.entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository  extends JpaRepository<SignUp,Integer> {
    boolean existsByUsername(String username);
    boolean existsByPhonenumber(String phonenumber);
    SignUp findByUsername(String username);
}
