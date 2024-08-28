package com.enigma.student_report.service;

import com.enigma.student_report.dto.request.NewTeacherRequest;
import com.enigma.student_report.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher inputTeacher(NewTeacherRequest teacher);
    Teacher getTeacherById(String id);
    List<Teacher> getAllTeachers(String name);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(String id);
}
