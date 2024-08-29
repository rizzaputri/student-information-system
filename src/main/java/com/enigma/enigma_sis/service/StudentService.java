package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.entity.Student;

import java.util.List;

public interface StudentService {
    Student inputStudent(Student student);
    Student getStudentById(String id);
    List<Student> getAllStudents(String name);
    Student updateStudent(Student student);
    void deleteStudent(String id);
}
