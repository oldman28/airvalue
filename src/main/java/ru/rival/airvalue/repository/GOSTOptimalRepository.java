package ru.rival.airvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rival.airvalue.domain.GOSTOptimal;
import ru.rival.airvalue.service.api.dto.Place;
import ru.rival.airvalue.service.api.dto.Season;

import java.util.Optional;

@Repository
public interface GOSTOptimalRepository extends JpaRepository<GOSTOptimal, Long> {

    Optional<GOSTOptimal> findBySeasonAndPlace(Season season, Place place);
}
