package com.ffx64.searchx_api.entity.searchx;

import java.io.Serializable;

import com.ffx64.searchx_api.entity.searchx.UserOperationEntity.UserOperationId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="user_operations")
@IdClass(UserOperationId.class)
public class UserOperationEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "operation_id")
    private Long operationId;

    @Column(name = "role")
    private Integer role;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserOperationId implements Serializable {
        private Long userId;
        private Long operationId;
    }
}