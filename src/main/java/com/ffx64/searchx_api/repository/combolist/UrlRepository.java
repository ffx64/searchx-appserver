package com.ffx64.searchx_api.repository.combolist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.combolist.UrlEntity;

@Repository("combolistUrlRepository")
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    
    List<UrlEntity> findByUserId(Long userId);

    boolean existsByUserIdAndUrl(Long userId, String url);
    
    List<UrlEntity> findByCreatedAt(java.util.Date createdAt);
}
