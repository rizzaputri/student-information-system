package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findAllByNameLike(String name);
}
