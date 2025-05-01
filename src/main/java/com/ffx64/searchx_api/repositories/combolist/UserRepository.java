package com.ffx64.searchx_api.repositories.combolist;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entities.combolist.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByFileId(Long fileId);

    List<UserEntity> findByCreatedAt(Date createdAt);
    
    List<UserEntity> findByUsernameIgnoreCase(String username);

    List<UserEntity> findByPasswordIgnoreCase(String password);
}
