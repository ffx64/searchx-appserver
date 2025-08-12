package com.ffx64.searchx_api.repository.combolist;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.combolist.DataEntity;

@Repository("CombolistDataRepository")
public interface DataRepository extends JpaRepository<DataEntity, UUID> {

    List<DataEntity> findByUsername(String username);
    
    List<DataEntity> findByEmail(String email);

    List<DataEntity> findByPassword(String password);
    
    List<DataEntity> findByDomain(String domain);

    List<DataEntity> findByMetadataId(UUID id);

    @Query("SELECT d FROM DataEntity d WHERE LOWER(d.email) LIKE LOWER(CONCAT('%', :input, '%')) OR LOWER(d.username) LIKE LOWER(CONCAT('%', :input, '%'))")
    Page<DataEntity> findBySimilarEmailOrUsername(@Param("input") String input, Pageable pageable);

    @Query("SELECT d FROM DataEntity d WHERE d.metadata.id = :metadataId AND (LOWER(d.email) LIKE LOWER(CONCAT('%', :input, '%')) OR LOWER(d.username) LIKE LOWER(CONCAT('%', :input, '%')))")
    Page<DataEntity> findByMetadataIdAndSimilarEmailOrUsername(@Param("metadataId") UUID metadataId, @Param("input") String input, Pageable pageable);
}
