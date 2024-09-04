package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.SubjectRequest;
import com.enigma.enigma_sis.dto.response.SubjectResponse;
import com.enigma.enigma_sis.entity.Subject;
import org.springframework.data.domain.Page;

public interface SubjectService {
    SubjectResponse createSubject(SubjectRequest subject);
    Subject getById(String id);
    SubjectResponse getSubjectById(String id);
    Page<Subject> getAllSubjects(String name, String page);
    SubjectResponse updateSubject(SubjectRequest subject);
    void deleteById(String id);
}
