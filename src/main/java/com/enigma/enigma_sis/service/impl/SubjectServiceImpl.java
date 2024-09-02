package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.SubjectRequest;
import com.enigma.enigma_sis.dto.response.SubjectResponse;
import com.enigma.enigma_sis.entity.Subject;
import com.enigma.enigma_sis.entity.Teacher;
import com.enigma.enigma_sis.repository.SubjectRepository;
import com.enigma.enigma_sis.service.SubjectService;
import com.enigma.enigma_sis.service.TeacherService;
import com.enigma.enigma_sis.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherService teacherService;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SubjectResponse createSubject(SubjectRequest newSubject) {
        validationUtil.validate(newSubject);

        Teacher teacher = teacherService.getById(newSubject.getTeacherId());

        Subject subject = Subject.builder()
                .name(newSubject.getName())
                .lessonsHours(newSubject.getLessonsHours())
                .teacher(teacher)
                .build();
        subjectRepository.saveAndFlush(subject);

        return convertToSubjectResponse(subject);
    }

    @Transactional(readOnly = true)
    @Override
    public Subject getById(String id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ConstantMessage.NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public SubjectResponse getSubjectById(String id) {
        Subject subject = getById(id);
        return convertToSubjectResponse(subject);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SubjectResponse> getAllSubjects(String name) {
        if (name != null) {
            List<Subject> subjectParameterList = subjectRepository.findAllByNameLike("%" + name + "%");
            return subjectParameterList.stream().map(this::convertToSubjectResponse).toList();
        }
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList.stream().map(this::convertToSubjectResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SubjectResponse updateSubject(SubjectRequest subject) {
        validationUtil.validate(subject);

        Subject currentSubject = getById(subject.getId());
        Teacher teacher = teacherService.getById(subject.getTeacherId());

        currentSubject.setName(subject.getName());
        currentSubject.setLessonsHours(subject.getLessonsHours());
        currentSubject.setTeacher(teacher);

        subjectRepository.saveAndFlush(currentSubject);
        return convertToSubjectResponse(currentSubject);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Subject currentSubject = getById(id);
        subjectRepository.delete(currentSubject);
    }


    private SubjectResponse convertToSubjectResponse(Subject subject) {
        return SubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .lessonsHours(subject.getLessonsHours())
                .teacherId(subject.getTeacher().getId())
                .build();
    }
}
