package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.UpdateTeacherRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.dto.response.PagingResponse;
import com.enigma.enigma_sis.dto.response.TeacherResponse;
import com.enigma.enigma_sis.entity.Teacher;
import com.enigma.enigma_sis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.TEACHER_URL)
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<TeacherResponse>> getTeacherById(
            @PathVariable String id
    ) {
        TeacherResponse teacher = teacherService.getTeacherById(id);

        CommonResponse<TeacherResponse> response = CommonResponse
                .<TeacherResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCESS + teacher.getName())
                .data(teacher)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TeacherResponse>>> getAllTeachers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page") String page
    ) {
        Page<Teacher> pagedTeachers = teacherService.getAllTeachers(name, page);

        List<TeacherResponse> teachers = pagedTeachers.stream().map(teacher ->
                TeacherResponse.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .status(teacher.getStatus())
                        .teacherEmail(teacher.getTeacherEmail())
                        .userAccountId(teacher.getUserAccount().getId())
                        .build())
                .toList();

        CommonResponse<List<TeacherResponse>> response = CommonResponse
                .<List<TeacherResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCESS + "all teachers")
                .data(teachers)
                .paging(PagingResponse.builder()
                        .totalPage(pagedTeachers.getTotalPages())
                        .totalElement(pagedTeachers.getTotalElements())
                        .page(pagedTeachers.getNumber() + 1)
                        .size(2)
                        .hasPrevious(pagedTeachers.hasPrevious())
                        .hasNext(pagedTeachers.hasNext())
                        .build())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TeacherResponse>> updateTeacher(
            @RequestBody UpdateTeacherRequest teacher
    ) {
        TeacherResponse updateTeacher = teacherService.updateTeacher(teacher);

        CommonResponse<TeacherResponse> response = CommonResponse
                .<TeacherResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCESS + updateTeacher.getName())
                .data(updateTeacher)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> updateStatus(
            @PathVariable String id, @RequestParam(name = "status") Boolean status
    ) {
        teacherService.updateStatusById(id, status);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCESS + teacherService.getById(id).getName())
                .data("OK")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteTeacher(
            @PathVariable String id
    ) {
        teacherService.deleteById(id);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.DELETE_SUCCESS + id)
                .data("OK")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
