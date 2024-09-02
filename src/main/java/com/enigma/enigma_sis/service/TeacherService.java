package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.UpdateTeacherRequest;
import com.enigma.enigma_sis.dto.response.TeacherResponse;
import com.enigma.enigma_sis.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher inputTeacher(Teacher teacher);
    Teacher getById(String id);
    TeacherResponse getTeacherById(String id);
    List<TeacherResponse> getAllTeachers(String name);
    TeacherResponse updateTeacher(UpdateTeacherRequest teacher);
    void updateStatusById(String id, Boolean status);
    void deleteById(String id);
}
