package com.finaltime.catalogue_management.repository;

import com.finaltime.catalogue_management.entity.Snake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnakeRepository extends JpaRepository<Snake, Long> {
}