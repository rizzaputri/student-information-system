package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.NewSubjectRequest;
import com.enigma.enigma_sis.entity.Subject;

import java.util.List;

public interface SubjectService {
    Subject inputSubject(NewSubjectRequest subject);
    Subject getSubjectById(String id);
    List<Subject> getAllSubjects(String name);
    Subject updateSubject(Subject subject);
    void deleteSubject(String id);
}
