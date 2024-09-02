package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    List<Teacher> findAllByNameLike(String name);

    @Modifying
    @Query(value = "UPDATE m_teacher SET STATUS = :status WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") String id, @Param("status") Boolean status);
}
