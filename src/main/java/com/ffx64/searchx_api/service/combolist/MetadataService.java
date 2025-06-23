package com.ffx64.searchx_api.service.combolist;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.combolist.MetadataResponseDTO;
import com.ffx64.searchx_api.entity.combolist.MetadataEntity;
import com.ffx64.searchx_api.exception.MetadataNotFoundException;
import com.ffx64.searchx_api.repository.combolist.MetadataRepository;

@Service("CombolistMetadataService")
public class MetadataService {

    @Autowired
    MetadataRepository repository;

    public MetadataResponseDTO getById(UUID id) {
        MetadataEntity metadata = repository.findById(id).orElseThrow(() -> new MetadataNotFoundException());

        return new MetadataResponseDTO(
            metadata.getId(),
            metadata.getSource(),
            metadata.getCollectedAt(),
            metadata.getTags(),
            metadata.getNotes()
        );
    }

    public Page<MetadataResponseDTO> getAllPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<MetadataEntity> dataPage = repository.findAllPaginated(pageable);

        return dataPage.map(d -> new MetadataResponseDTO(
            d.getId(),
            d.getSource(),
            d.getCollectedAt(),
            d.getTags(),
            d.getNotes()
        ));
    }    
}
