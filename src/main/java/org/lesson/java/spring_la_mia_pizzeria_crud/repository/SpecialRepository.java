package org.lesson.java.spring_la_mia_pizzeria_crud.repository;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Special;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialRepository extends JpaRepository<Special, Integer> {
    
}
