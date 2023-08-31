package com.amo.lse.repository;

import com.amo.lse.model.IssuerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRepository extends JpaRepository<IssuerEntity, Long> {
}
