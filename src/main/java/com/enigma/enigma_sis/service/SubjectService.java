package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.SubjectRequest;
import com.enigma.enigma_sis.dto.response.SubjectResponse;
import com.enigma.enigma_sis.entity.Subject;

import java.util.List;

public interface SubjectService {
    SubjectResponse createSubject(SubjectRequest subject);
    Subject getById(String id);
    SubjectResponse getSubjectById(String id);
    List<SubjectResponse> getAllSubjects(String name);
    SubjectResponse updateSubject(SubjectRequest subject);
    void deleteById(String id);
}
