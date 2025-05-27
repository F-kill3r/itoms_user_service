package com.capston_design.fkiller.itoms.user_service.model;

import com.capston_design.fkiller.itoms.user_service.model.common.BaseEntity;
import com.capston_design.fkiller.itoms.user_service.model.enums.UserCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table(name= "t_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserCategory category;

    @PrePersist
    public void assignUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}
