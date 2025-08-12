package com.ffx64.searchx_api.service.combolist;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.combolist.DataMResponseDTO;
import com.ffx64.searchx_api.dto.combolist.DataResponseDTO;
import com.ffx64.searchx_api.dto.combolist.MetadataResponseDTO;
import com.ffx64.searchx_api.entity.combolist.DataEntity;
import com.ffx64.searchx_api.exception.MetadataNotFoundException;
import com.ffx64.searchx_api.repository.combolist.DataRepository;

@Service("CombolistDataService")
public class DataService {

    @Autowired
    DataRepository repository;

    public List<DataResponseDTO> getBySimilar(String input, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        List<DataEntity> results = repository.findBySimilarEmailOrUsername(input.toLowerCase(), pageable).getContent();

        return results.stream()
            .map(data -> new DataResponseDTO(
                data.getId(),
                data.getEmail(),
                data.getUsername(),
                data.getPassword(),
                data.getDomain()
            ))
            .toList();
    }

    public List<DataResponseDTO> getByMetadataSimilar(UUID metadataId, String input, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        List<DataEntity> results = repository.findByMetadataIdAndSimilarEmailOrUsername(metadataId, input.toLowerCase(), pageable).getContent();

        return results.stream()
            .map(data -> new DataResponseDTO(
                data.getId(),
                data.getEmail(),
                data.getUsername(),
                data.getPassword(),
                data.getDomain()
            ))
            .toList();
    }

    public DataMResponseDTO getById(UUID id) {
        DataEntity entry = repository.findById(id)
                            .orElseThrow(() -> new MetadataNotFoundException());
    
        MetadataResponseDTO metadata = new MetadataResponseDTO(
            entry.getMetadata().getId(),
            entry.getMetadata().getSource(),
            entry.getMetadata().getCollectedAt(),
            entry.getMetadata().getTags(),
            entry.getMetadata().getNotes()
        );

        return new DataMResponseDTO(
            entry.getId(),
            entry.getEmail(),
            entry.getUsername(), 
            entry.getPassword(), 
            entry.getDomain(), 
            metadata
            );
    }

    public List<DataResponseDTO> getByEmail(String email) {
        List<DataEntity> entries = repository.findByEmail(email);
        return mapToDTOList(entries);
    }

    public List<DataResponseDTO> getByUsername(String username) {
        List<DataEntity> entries = repository.findByUsername(username);
        return mapToDTOList(entries);
    }

    public List<DataResponseDTO> getByPassword(String password) {
        List<DataEntity> entries = repository.findByPassword(password);
        return mapToDTOList(entries);
    }

    public List<DataResponseDTO> getByDomain(String domain) {
        List<DataEntity> entries = repository.findByDomain(domain);
        return mapToDTOList(entries);
    }

    private List<DataResponseDTO> mapToDTOList(List<DataEntity> entries) {
        return entries.stream()
            .map(entry -> new DataResponseDTO(
                entry.getId(),
                entry.getEmail(),
                entry.getUsername(),
                entry.getPassword(),
                entry.getDomain()
            ))
            .toList();
    }
}
