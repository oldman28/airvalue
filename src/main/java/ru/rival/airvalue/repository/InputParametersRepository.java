package ru.rival.airvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rival.airvalue.domain.InputParameters;

@Repository
public interface InputParametersRepository extends JpaRepository<InputParameters, Long> {
}
