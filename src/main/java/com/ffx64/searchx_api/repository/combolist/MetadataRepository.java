package com.ffx64.searchx_api.repository.combolist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.combolist.MetadataEntity;

@Repository("CombolistMetadataRepository")
public interface MetadataRepository extends JpaRepository<MetadataEntity, Long> {

    @Query("SELECT d FROM MetadataEntity d")
    Page<MetadataEntity> findAllPaginated(Pageable pageable);
}
