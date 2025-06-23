package com.ffx64.searchx_api.repository.combolist;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.combolist.DataEntity;

@Repository("CombolistDataRepository")
public interface DataRepository extends JpaRepository<DataEntity, UUID> {

    List<DataEntity> findByEmail(String email);

    List<DataEntity> findByUsername(String username);

    List<DataEntity> findByDomain(String domain);

    List<DataEntity> findByPassword(String password);

    List<DataEntity> findByMetadataId(UUID id);

    @Query("SELECT d FROM DataEntity d WHERE LOWER(d.email) LIKE LOWER(CONCAT('%', :input, '%')) OR LOWER(d.username) LIKE LOWER(CONCAT('%', :input, '%'))")
    List<DataEntity> findBySimilarEmailOrUsername(@Param("input") String input);
}
