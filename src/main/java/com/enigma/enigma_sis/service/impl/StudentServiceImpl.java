package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.entity.Student;
import com.enigma.enigma_sis.repository.StudentRepository;
import com.enigma.enigma_sis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student inputStudent(Student student) {
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
            return studentRepository.findAllByNameLike("%" + name + "%");
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
