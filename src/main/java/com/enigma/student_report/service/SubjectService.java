package com.enigma.student_report.service;

import com.enigma.student_report.dto.request.NewSubjectRequest;
import com.enigma.student_report.entity.Subject;

import java.util.List;

public interface SubjectService {
    Subject inputSubject(NewSubjectRequest subject);
    Subject getSubjectById(String id);
    List<Subject> getAllSubjects(String name);
    Subject updateSubject(Subject subject);
    void deleteSubject(String id);
}
