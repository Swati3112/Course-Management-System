package com.app.UserManagement.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.UserManagement.model.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findByFirstNameIgnoreCaseContaining(String firstName);

    List<User> findByLastNameIgnoreCaseContaining(String lastName);

    List<User> findByEmailIgnoreCaseContaining(String email);
    
    Optional<User> findByUserName(String userName);

	boolean existsByUserName(String username);

	boolean existsByEmail(String email);

}