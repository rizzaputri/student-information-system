package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.UpdateStudentRequest;
import com.enigma.enigma_sis.dto.response.StudentResponse;
import com.enigma.enigma_sis.entity.Student;
import com.enigma.enigma_sis.entity.UserAccount;
import com.enigma.enigma_sis.repository.StudentRepository;
import com.enigma.enigma_sis.service.StudentService;
import com.enigma.enigma_sis.service.UserService;
import com.enigma.enigma_sis.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final UserService userService;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Student inputStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Transactional(readOnly = true)
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
        validationUtil.validate(student);

        Student currentStudent = getById(student.getId());

        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(currentStudent.getUserAccount().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    ConstantMessage.USER_INVALID);
        }

        currentStudent.setName(student.getName());
        currentStudent.setBirthDate(student.getBirthDate());
        currentStudent.setStudyGroup(student.getStudyGroup());
        currentStudent.setMobilePhone(student.getMobilePhone());
        studentRepository.saveAndFlush(currentStudent);

        return convertToStudentResponse(currentStudent);
    }

    @Transactional(rollbackFor = Exception.class)
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
