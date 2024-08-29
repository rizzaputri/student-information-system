package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.NewSubjectRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.entity.Subject;
import com.enigma.enigma_sis.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.SUBJECT_URL)
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<CommonResponse<Subject>> inputSubject(
            @RequestBody NewSubjectRequest newSubject
    ) {
        Subject subject = subjectService.inputSubject(newSubject);

        CommonResponse<Subject> response = CommonResponse
                .<Subject>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.INPUT_SUCCES + subject.getName())
                .data(subject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Subject>> getSubjectById(
            @PathVariable String id
    ) {
        Subject subject = subjectService.getSubjectById(id);

        CommonResponse<Subject> response = CommonResponse
                .<Subject>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + subject.getName())
                .data(subject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Subject>>> getAllSubjects(
            @RequestParam(name = "name", required = false) String name
    ) {
        List<Subject> subjects = subjectService.getAllSubjects(name);

        CommonResponse<List<Subject>> response = CommonResponse
                .<List<Subject>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + "all datas")
                .data(subjects)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Subject>> updateSubject(
            @RequestBody Subject subject
    ) {
        Subject updateSubject = subjectService.updateSubject(subject);

        CommonResponse<Subject> response = CommonResponse
                .<Subject>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCES + updateSubject.getName())
                .data(updateSubject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteSubject(
            @PathVariable String id
    ) {
        subjectService.deleteSubject(id);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.DELETE_SUCCES + id)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
