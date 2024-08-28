package com.enigma.student_report.service;

import com.enigma.student_report.dto.request.NewStudentRequest;
import com.enigma.student_report.entity.Student;

import java.util.List;

public interface StudentService {
    Student inputStudent(NewStudentRequest student);
    Student getStudentById(String id);
    List<Student> getAllStudents(String name);
    Student updateStudent(Student student);
    void deleteStudent(String id);
}
