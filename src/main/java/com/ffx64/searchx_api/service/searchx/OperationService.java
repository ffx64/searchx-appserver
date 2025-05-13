package com.ffx64.searchx_api.service.searchx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.searchx.OperationRequestDTO;
import com.ffx64.searchx_api.dto.searchx.OperationResponseDTO;
import com.ffx64.searchx_api.entity.searchx.OperationEntity;
import com.ffx64.searchx_api.entity.searchx.UserOperationEntity;
import com.ffx64.searchx_api.repository.searchx.OperationRepository;
import com.ffx64.searchx_api.repository.searchx.UserOperationRepository;

@Service("OperationServiceSearchx")
public class OperationService {
    
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    UserOperationRepository userOperationRepository;

    public OperationResponseDTO createOperation(OperationRequestDTO dto) {
        OperationEntity entity = new OperationEntity();

        entity.setName(dto.name());
        entity.setDescription(dto.description());

        OperationEntity saved = operationRepository.save(entity);
        
        UserOperationEntity userEntity = new UserOperationEntity();

        userEntity.setOperationId(saved.getId());

        userOperationRepository.save(userEntity);

        return toDTO(saved);
    }

    private OperationResponseDTO toDTO(OperationEntity entity) {
        return new OperationResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getStartDate(),
            entity.getEndDate()
        );
    }
}
