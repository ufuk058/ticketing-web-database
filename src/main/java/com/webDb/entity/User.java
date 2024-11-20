package com.webDb.entity;

import com.webDb.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
@Table(name="users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String phone;
    @Column(unique = true)
    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Role role;

    private Boolean isDeleted=false;

}
