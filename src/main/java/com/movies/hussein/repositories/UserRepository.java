package com.movies.hussein.repositories;

import com.movies.hussein.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity,Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UsersEntity findByUsername(String username);

    UsersEntity findByUserId(int userId);
}
