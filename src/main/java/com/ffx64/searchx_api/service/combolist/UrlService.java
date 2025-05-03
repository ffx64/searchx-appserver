package com.ffx64.searchx_api.service.combolist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.combolist.UrlResponseDTO;
import com.ffx64.searchx_api.dto.combolist.UserResponseDTO;
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

    private UserResponseDTO toUserDTO(UserEntity entity) {
        return UserResponseDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private UrlResponseDTO toDTO(UrlEntity entity) {
        return UrlResponseDTO.builder()
                .id(entity.getId())
                .user(this.toUserDTO(entity.getUser()))
                .url(entity.getUrl())
                .fileLine(entity.getFileLine())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
