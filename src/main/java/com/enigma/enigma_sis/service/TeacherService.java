package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.UpdateTeacherRequest;
import com.enigma.enigma_sis.dto.response.TeacherResponse;
import com.enigma.enigma_sis.entity.Subject;
import com.enigma.enigma_sis.entity.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    Teacher inputTeacher(Teacher teacher);
    Teacher getById(String id);
    TeacherResponse getTeacherById(String id);
    Page<Teacher> getAllTeachers(String name, String page);
    TeacherResponse updateTeacher(UpdateTeacherRequest teacher);
    void updateStatusById(String id, Boolean status);
    void deleteById(String id);
}
