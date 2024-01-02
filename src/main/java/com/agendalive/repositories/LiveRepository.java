package com.agendalive.repositories;

import com.agendalive.domains.Live;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LiveRepository extends JpaRepository<Live, UUID> {
}
