package com.webDb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@NoArgsConstructor
@Data
public class Role extends BaseEntity{

    private String description;
}
