package com.webDb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor
@Data
@Table(name="projects")
@Where(clause = "is+Deleted=false")
public class Project {
}
