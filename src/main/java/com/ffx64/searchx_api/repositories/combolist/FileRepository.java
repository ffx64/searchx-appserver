package com.ffx64.searchx_api.repositories.combolist;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entities.combolist.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByStatus(Integer status);

    List<FileEntity> findByCreatedAt(Date createdAt);

    FileEntity findByHash(String hash);
}
