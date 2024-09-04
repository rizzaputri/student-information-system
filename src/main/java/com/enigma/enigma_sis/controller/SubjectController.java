package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.SubjectRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.dto.response.PagingResponse;
import com.enigma.enigma_sis.dto.response.SubjectResponse;
import com.enigma.enigma_sis.entity.Subject;
import com.enigma.enigma_sis.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.SUBJECT_URL)
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<CommonResponse<SubjectResponse>> inputSubject(
            @RequestBody SubjectRequest newSubject
    ) {
        SubjectResponse subject = subjectService.createSubject(newSubject);

        CommonResponse<SubjectResponse> response = CommonResponse
                .<SubjectResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.INPUT_SUCCESS + subject.getName())
                .data(subject)
                .paging(null)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<SubjectResponse>> getSubjectById(
            @PathVariable String id
    ) {
        SubjectResponse subject = subjectService.getSubjectById(id);

        CommonResponse<SubjectResponse> response = CommonResponse
                .<SubjectResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCESS + subject.getName())
                .data(subject)
                .paging(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<SubjectResponse>>> getAllSubjects(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page") String page
    ) {
        Page<Subject> pagedSubjects = subjectService.getAllSubjects(name, page);

        List<SubjectResponse> subjects = pagedSubjects.getContent().stream().map(subject ->
            SubjectResponse.builder()
                    .id(subject.getId())
                    .name(subject.getName())
                    .lessonsHours(subject.getLessonsHours())
                    .teacherId(subject.getId())
                    .build()
        ).toList();

        CommonResponse<List<SubjectResponse>> response = CommonResponse
                .<List<SubjectResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCESS + "all subjects")
                .data(subjects)
                .paging(PagingResponse.builder()
                        .totalPage(pagedSubjects.getTotalPages())
                        .totalElement(pagedSubjects.getTotalElements())
                        .page(pagedSubjects.getNumber() + 1)
                        .size(2)
                        .hasNext(pagedSubjects.hasNext())
                        .hasPrevious(pagedSubjects.hasPrevious())
                        .build())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping
    public ResponseEntity<CommonResponse<SubjectResponse>> updateSubject(
            @RequestBody SubjectRequest subject
    ) {
        SubjectResponse updateSubject = subjectService.updateSubject(subject);

        CommonResponse<SubjectResponse> response = CommonResponse
                .<SubjectResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCESS + updateSubject.getName())
                .data(updateSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteSubject(
            @PathVariable String id
    ) {
        subjectService.deleteById(id);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.DELETE_SUCCESS + id)
                .data("OK")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
