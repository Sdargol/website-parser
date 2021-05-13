package com.sdargol.repositories;

import com.sdargol.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Integer> {
    Site findByName(String name);
}
