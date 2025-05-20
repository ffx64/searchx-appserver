package com.ffx64.searchx_api.repository.main;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.main.UserEntity;

@Repository("MainUserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    default UserDetails findByUsernameDetails(String username) {
        Optional<UserEntity> userEntity = findByUsername(username);
        return userEntity.map(u -> new org.springframework.security.core.userdetails.User(
                u.getUsername(), 
                u.getPassword(), 
                u.getAuthorities()))  
                .orElse(null);
    }

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLogin = CURRENT_TIMESTAMP WHERE u.id = :userId")
    void updateLastLogin(@Param("userId") Long userId);
}
