package com.enigma.student_report.controller;

import com.enigma.student_report.constant.ApiUrl;
import com.enigma.student_report.constant.ConstantMessage;
import com.enigma.student_report.dto.request.NewStudentRequest;
import com.enigma.student_report.dto.response.CommonResponse;
import com.enigma.student_report.entity.Student;
import com.enigma.student_report.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.STUDENT_URL)
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<CommonResponse<Student>> inputStudent(
            @RequestBody NewStudentRequest newStudent
    ) {
        Student student = studentService.inputStudent(newStudent);

        CommonResponse<Student> response = CommonResponse
                .<Student>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.INPUT_SUCCES + student.getName())
                .data(student)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Student>> getStudentById(
            @PathVariable String id
    ) {
        Student student = studentService.getStudentById(id);

        CommonResponse<Student> response = CommonResponse
                .<Student>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + student.getName())
                .data(student)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Student>>> getAllStudents(
            @RequestParam(name = "name", required = false) String name
    ) {
        List<Student> students = studentService.getAllStudents(name);

        CommonResponse<List<Student>> response = CommonResponse
                .<List<Student>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.FETCH_SUCCES + "all datas")
                .data(students)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Student>> updateStudent(
            @RequestBody Student student
    ) {
        Student updateStudent = studentService.updateStudent(student);

        CommonResponse<Student> response = CommonResponse
                .<Student>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.UPDATE_SUCCES + updateStudent.getName())
                .data(updateStudent)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteStudent(
            @PathVariable String id
    ) {
        studentService.deleteStudent(id);

        CommonResponse<?> response = CommonResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message(ConstantMessage.DELETE_SUCCES + id)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
