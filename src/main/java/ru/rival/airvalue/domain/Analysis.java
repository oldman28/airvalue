package ru.rival.airvalue.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
public class Analysis {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn
    private User user;

    @OneToOne
    @JoinColumn
    private InputParameters inputParameters;

    private String name;

    private String recommendationText;

    private LocalDateTime dateTime;

}
