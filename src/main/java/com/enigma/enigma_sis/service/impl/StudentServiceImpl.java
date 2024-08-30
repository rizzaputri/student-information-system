package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.UpdateStudentRequest;
import com.enigma.enigma_sis.dto.response.StudentResponse;
import com.enigma.enigma_sis.entity.Student;
import com.enigma.enigma_sis.repository.StudentRepository;
import com.enigma.enigma_sis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Student inputStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ConstantMessage.NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public StudentResponse getStudentById(String id) {
        Student student = getById(id);
        return convertToStudentResponse(student);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentResponse> getAllStudents(String name) {
        if (name != null) {
            List<Student> studentParameterList = studentRepository.findAllByNameLike("%" + name + "%");
            return studentParameterList.stream().map(this::convertToStudentResponse).toList();
        }
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(this::convertToStudentResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudentResponse updateStudent(UpdateStudentRequest student) {
        Student currentStudent = getById(student.getId());
        currentStudent.setName(student.getName());
        currentStudent.setStudyGroup(student.getStudyGroup());
        currentStudent.setMobilePhone(student.getMobilePhone());
        currentStudent.setStudentEmail(student.getStudentEmail());
        studentRepository.saveAndFlush(currentStudent);
        return convertToStudentResponse(currentStudent);
    }

    @Override
    public void deleteById(String id) {
        Student currentStudent = getById(id);
        studentRepository.delete(currentStudent);
    }


    private StudentResponse convertToStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .studyGroup(student.getStudyGroup())
                .studentEmail(student.getStudentEmail())
                .userAccountId(student.getUserAccount().getId())
                .build();
    }
}
