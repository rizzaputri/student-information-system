package com.enigma.student_report.service.impl;

import com.enigma.student_report.constant.ConstantMessage;
import com.enigma.student_report.dto.request.NewSubjectRequest;
import com.enigma.student_report.entity.Subject;
import com.enigma.student_report.entity.Teacher;
import com.enigma.student_report.repository.SubjectRepository;
import com.enigma.student_report.service.SubjectService;
import com.enigma.student_report.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherService teacherService;

    @Override
    public Subject inputSubject(NewSubjectRequest newSubject) {
        Teacher teacher = teacherService.getTeacherById(newSubject.getTeacherId());

        Subject subject = Subject
                .builder()
                .name(newSubject.getName())
                .lessonsHours(newSubject.getLessonsHours())
                .teacher(teacher)
                .build();
        return subjectRepository.saveAndFlush(subject);
    }

    @Override
    public Subject getSubjectById(String id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ConstantMessage.NOT_FOUND)
        );
    }

    @Override
    public List<Subject> getAllSubjects(String name) {
        if (name != null) {
            return subjectRepository.findAllByNameLike("%" + name + "%");
        }
        return subjectRepository.findAll();
    }

    @Override
    public Subject updateSubject(Subject subject) {
        getSubjectById(subject.getId());
        return subjectRepository.saveAndFlush(subject);
    }

    @Override
    public void deleteSubject(String id) {
        Subject currentSubject = getSubjectById(id);
        subjectRepository.delete(currentSubject);
    }
}
