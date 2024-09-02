package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.UpdateStudentRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.dto.response.StudentResponse;
import com.enigma.enigma_sis.entity.Student;
import com.enigma.enigma_sis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.STUDENT_URL)
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<StudentResponse>> getStudentById(
            @PathVariable String id
    ) {
        StudentResponse student = studentService.getStudentById(id);

        CommonResponse<StudentResponse> response = CommonResponse
                .<StudentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + student.getName())
                .data(student)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<StudentResponse>>> getAllStudents(
            @RequestParam(name = "name", required = false) String name
    ) {
        List<StudentResponse> students = studentService.getAllStudents(name);

        CommonResponse<List<StudentResponse>> response = CommonResponse
                .<List<StudentResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + "all students")
                .data(students)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<StudentResponse>> updateStudent(
            @RequestBody UpdateStudentRequest student
    ) {
        StudentResponse updateStudent = studentService.updateStudent(student);

        CommonResponse<StudentResponse> response = CommonResponse
                .<StudentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCES + updateStudent.getName())
                .data(updateStudent)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteStudent(
            @PathVariable String id
    ) {
        studentService.deleteById(id);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.DELETE_SUCCES + id)
                .data("OK")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
