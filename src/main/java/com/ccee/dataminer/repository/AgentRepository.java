package com.ccee.dataminer.repository;

import com.ccee.dataminer.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
