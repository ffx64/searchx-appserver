package com.ffx64.searchx_api.repository.searchx;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.searchx.UserEntity;

@Repository("searchxUserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByUsername(String username);
}
