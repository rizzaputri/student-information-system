package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    List<Teacher> findAllByNameLike(String name);
}
