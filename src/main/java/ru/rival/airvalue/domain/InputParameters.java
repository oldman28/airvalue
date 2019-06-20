package ru.rival.airvalue.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.rival.airvalue.service.api.dto.Place;
import ru.rival.airvalue.service.api.dto.Season;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
public class InputParameters {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn
    private User user;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    private Place place;

    private Integer area;

    private Integer height;

    private Integer budget;

    private Integer temperature;

    private Integer wetness;
}
