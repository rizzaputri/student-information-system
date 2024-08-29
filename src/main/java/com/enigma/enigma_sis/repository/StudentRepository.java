package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllByNameLike(String name);
}
