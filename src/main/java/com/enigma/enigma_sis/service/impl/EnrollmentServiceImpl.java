package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.EnrollmentRequest;
import com.enigma.enigma_sis.dto.response.EnrollmentDetailResponse;
import com.enigma.enigma_sis.dto.response.EnrollmentResponse;
import com.enigma.enigma_sis.entity.*;
import com.enigma.enigma_sis.repository.EnrollmentRepository;
import com.enigma.enigma_sis.service.*;
import com.enigma.enigma_sis.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    private final EnrollmentDetailService enrollmentDetailService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final UserService userService;

    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EnrollmentResponse create(EnrollmentRequest enrollmentRequest) {
        // Validasi input
        validationUtil.validate(enrollmentRequest);

        // Cari objek Student sesuai dengan ID dari Enrollment Request
        Student studentById = studentService.getById(enrollmentRequest.getStudentId());

        // Cek apakah yang membuat enrollment adalah student yang bersangkutan
        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(studentById.getUserAccount().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    ConstantMessage.USER_INVALID);
        }

        // Buat objek Enrollment tanpa Enrollment Detail dan simpan
        Enrollment enrollment = Enrollment
                .builder()
                .student(studentById)
                .enrollmentTerm(enrollmentRequest.getEnrollmentTerm())
                .build();
        enrollmentRepository.saveAndFlush(enrollment);

        // Buat Enrollment Detail
        List<EnrollmentDetail> enrollmentDetails = enrollmentRequest
                .getEnrollmentDetails().stream().map(
                        detailRequest -> {
                            return EnrollmentDetail.builder()
                                    .enrollment(enrollment)
                                    .subject(subjectService.getById(detailRequest.getSubjectId()))
                                    .teacher(teacherService.getById(
                                            subjectService.getById(detailRequest.getSubjectId())
                                                    .getTeacher().getId()))
                                    .build();
                        }).toList();

        // Simpan Enrollment Detail
        enrollmentDetailService.createBulk(enrollmentDetails);

        // Tambah Enrollment Detail ke dalam Enrollment dan simpan
        enrollment.setEnrollmentDetails(enrollmentDetails);

        // Buat Enrollment Detail Response untuk Enrollment Response
        List<EnrollmentDetailResponse> detailResponses = enrollmentDetails
                .stream().map(response -> EnrollmentDetailResponse.builder()
                        .id(response.getId())
                        .subjectId(response.getSubject().getId())
                        .lessonsHours(response.getSubject().getLessonsHours())
                        .teacherId(response.getTeacher().getId())
                        .build()
                ).collect(Collectors.toList());

        // Buat Enrollment Response
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .enrollmentTerm(enrollment.getEnrollmentTerm())
                .enrollmentDetails(detailResponses)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<EnrollmentResponse> getAll(String studentId) {
        Student studentById = studentService.getById(studentId);

        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(studentById.getUserAccount().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    ConstantMessage.USER_INVALID);
        }

        List<Enrollment> enrollments = enrollmentRepository.findAllByStudentId(studentId);

        return enrollments.stream()
                .map(enrollment -> {
                    List<EnrollmentDetailResponse> detailResponses = enrollment.getEnrollmentDetails().stream()
                            .map(response -> {
                                return EnrollmentDetailResponse.builder()
                                        .id(response.getId())
                                        .subjectId(response.getSubject().getId())
                                        .lessonsHours(response.getSubject().getLessonsHours())
                                        .teacherId(response.getTeacher().getId())
                                        .build();
                            }).collect(Collectors.toList());

                    return EnrollmentResponse.builder()
                            .id(enrollment.getId())
                            .studentId(enrollment.getStudent().getId())
                            .enrollmentTerm(enrollment.getEnrollmentTerm())
                            .enrollmentDetails(detailResponses)
                            .build();
                }).collect(Collectors.toList());
    }
}
