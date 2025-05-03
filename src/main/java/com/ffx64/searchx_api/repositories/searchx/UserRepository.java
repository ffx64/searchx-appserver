package com.ffx64.searchx_api.repositories.searchx;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entities.searchx.UserEntity;

@Repository("searchxUserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
