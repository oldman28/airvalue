package ru.rival.airvalue.domain;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Immutable;
import ru.rival.airvalue.service.api.dto.Place;
import ru.rival.airvalue.service.api.dto.Season;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
public class GOSTOptimal {

    @Id
    @GeneratedValue
    private Long Id;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    private Place place;

    private Integer Temperature;

    private Integer Wetness;
}
