package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.UpdateStudentRequest;
import com.enigma.enigma_sis.dto.response.StudentResponse;
import com.enigma.enigma_sis.entity.Student;

import java.util.List;

public interface StudentService {
    Student inputStudent(Student student);
    Student getById(String id);
    StudentResponse getStudentById(String id);
    List<StudentResponse> getAllStudents(String name);
    StudentResponse updateStudent(UpdateStudentRequest student);
    void deleteById(String id);
}
