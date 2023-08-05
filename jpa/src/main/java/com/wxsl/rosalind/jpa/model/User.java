package com.wxsl.rosalind.jpa.model;

import com.wxsl.rosalind.jpa.configuration.AuditableEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author wxsl1997
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String password;

    @Version
    Long version;
}
