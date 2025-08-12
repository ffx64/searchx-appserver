package com.ffx64.searchx_api.repository.combolist;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.ffx64.searchx_api.entity.combolist.MetadataEntity;

@Repository("CombolistMetadataRepository")
public interface MetadataRepository extends JpaRepository<MetadataEntity, UUID> {

    @Query("SELECT d FROM MetadataEntity d")
    Page<MetadataEntity> findAllPaginated(Pageable pageable);

    @Query("""
        SELECT d.metadata.id, COUNT(d)
        FROM DataEntity d
        WHERE LOWER(d.email) LIKE LOWER(CONCAT('%', :input, '%'))
        OR LOWER(d.username) LIKE LOWER(CONCAT('%', :input, '%'))
        GROUP BY d.metadata.id
    """)
    List<Object[]> findMetadataCountBySimilar(@Param("input") String input);
}
