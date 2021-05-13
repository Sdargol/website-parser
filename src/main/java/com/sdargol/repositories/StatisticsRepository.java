package com.sdargol.repositories;

import com.sdargol.entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
}
