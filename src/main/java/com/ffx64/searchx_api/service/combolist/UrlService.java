package com.ffx64.searchx_api.service.combolist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.combolist.FileResponseDTO;
import com.ffx64.searchx_api.dto.combolist.UrlResponseDTO;
import com.ffx64.searchx_api.dto.combolist.UserResponseDTO;
import com.ffx64.searchx_api.entity.combolist.FileEntity;
import com.ffx64.searchx_api.entity.combolist.UrlEntity;
import com.ffx64.searchx_api.entity.combolist.UserEntity;
import com.ffx64.searchx_api.repository.combolist.UrlRepository;

@Service("UrlServiceCombolist")
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public List<UrlResponseDTO> getAllUrls() {
        return urlRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UrlResponseDTO getUrlById(Long id) {
        return urlRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    private FileResponseDTO toFileDTO(FileEntity entity) {
        return new FileResponseDTO(
            entity.getId(),
            entity.getAgentKey(),
            entity.getName(),
            entity.getSize(),
            entity.getHash(),
            entity.getCreatedAt(),
            entity.getProcessedAt(),
            entity.getStatus(),
            entity.getSource(),
            entity.getType(),
            entity.getDescription(),
            entity.getProcessedEntriesCount()
        );
    }

    private UserResponseDTO toUserDTO(UserEntity entity) {
        return new UserResponseDTO(
            entity.getId(),
            this.toFileDTO(entity.getFile()),
            entity.getUsername(),
            entity.getPassword(),
            entity.getCreatedAt()
        );
    }
 
    private UrlResponseDTO toDTO(UrlEntity entity) {
        return new UrlResponseDTO(
            entity.getId(),
            this.toUserDTO(entity.getUser()),
            entity.getUrl(),
            entity.getFileLine(),
            entity.getCreatedAt()
        );
    }
}
