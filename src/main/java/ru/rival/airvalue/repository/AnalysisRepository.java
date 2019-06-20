package ru.rival.airvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rival.airvalue.domain.Analysis;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
