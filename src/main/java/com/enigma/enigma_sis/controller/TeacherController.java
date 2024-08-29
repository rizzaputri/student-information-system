package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.NewTeacherRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.entity.Teacher;
import com.enigma.enigma_sis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.TEACHER_URL)
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<CommonResponse<Teacher>> createTeacher(
            @RequestBody NewTeacherRequest newTeacher
    ) {
        Teacher teacher = teacherService.inputTeacher(newTeacher);

        CommonResponse<Teacher> response = CommonResponse
                .<Teacher>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.INPUT_SUCCES + teacher.getName())
                .data(teacher)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Teacher>> getTeacherById(
            @PathVariable String id
    ) {
        Teacher teacher = teacherService.getTeacherById(id);

        CommonResponse<Teacher> response = CommonResponse
                .<Teacher>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + teacher.getName())
                .data(teacher)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Teacher>>> getAllTeachers(
            @RequestParam(name = "name", required = false) String name
    ) {
        List<Teacher> teachers = teacherService.getAllTeachers(name);

        CommonResponse<List<Teacher>> response = CommonResponse
                .<List<Teacher>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + "all datas")
                .data(teachers)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Teacher>> updateStudent(
            @RequestBody Teacher teacher
    ) {
        Teacher updateTeacher = teacherService.updateTeacher(teacher);

        CommonResponse<Teacher> response = CommonResponse
                .<Teacher>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCES + updateTeacher.getName())
                .data(updateTeacher)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteStudent(
            @PathVariable String id
    ) {
        teacherService.deleteTeacher(id);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.DELETE_SUCCES + id)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
