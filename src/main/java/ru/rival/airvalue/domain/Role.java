package ru.rival.airvalue.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
}
