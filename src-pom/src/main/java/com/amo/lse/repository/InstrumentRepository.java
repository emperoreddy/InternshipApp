package com.amo.lse.repository;

import com.amo.lse.model.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface InstrumentRepository extends JpaRepository<InstrumentEntity, Long> {
}
