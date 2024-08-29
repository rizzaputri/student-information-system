package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.NewTeacherRequest;
import com.enigma.enigma_sis.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher inputTeacher(NewTeacherRequest teacher);
    Teacher getTeacherById(String id);
    List<Teacher> getAllTeachers(String name);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(String id);
}
