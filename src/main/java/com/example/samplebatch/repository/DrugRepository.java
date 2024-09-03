package com.example.samplebatch.repository;

import com.example.samplebatch.entity.DrugEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugRepository extends JpaRepository<DrugEntity, Long> {
    Optional<DrugEntity> findByItemSeq(String itemSeq);
}
