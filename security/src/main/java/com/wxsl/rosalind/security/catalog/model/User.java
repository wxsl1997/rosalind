package com.wxsl.rosalind.security.catalog.model;

import com.wxsl.rosalind.security.jpa.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Version
    private Long version;
}
