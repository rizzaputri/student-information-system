package com.enigma.student_report.service.impl;

import com.enigma.student_report.constant.ConstantMessage;
import com.enigma.student_report.dto.request.NewStudentRequest;
import com.enigma.student_report.entity.Student;
import com.enigma.student_report.repository.StudentRepository;
import com.enigma.student_report.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student inputStudent(NewStudentRequest newStudent) {
        Student student = Student
                .builder()
                .name(newStudent.getName())
                .mobilePhone(newStudent.getMobilePhone())
                .build();

        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ConstantMessage.NOT_FOUND)
        );
    }

    @Override
    public List<Student> getAllStudents(String name) {
        if (name != null) {
            return studentRepository.findAllByNameLike(name);
        }
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        getStudentById(student.getId());
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(String id) {
        Student currentStudent = getStudentById(id);
        studentRepository.delete(currentStudent);
    }
}
