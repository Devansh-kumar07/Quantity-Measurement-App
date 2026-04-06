package com.quantitymeasurementapp.repository;

import com.quantitymeasurementapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    // Google OAuth2 ke liye - googleId se user dhundo
    Optional<UserEntity> findByGoogleId(String googleId);

    // Email se user dhundo (Google login fallback check)
    Optional<UserEntity> findByEmail(String email);
}
