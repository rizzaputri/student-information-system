package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.UpdateTeacherRequest;
import com.enigma.enigma_sis.dto.response.TeacherResponse;
import com.enigma.enigma_sis.entity.Teacher;
import com.enigma.enigma_sis.entity.UserAccount;
import com.enigma.enigma_sis.repository.TeacherRepository;
import com.enigma.enigma_sis.service.TeacherService;
import com.enigma.enigma_sis.service.UserService;
import com.enigma.enigma_sis.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    private final UserService userService;

    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Teacher inputTeacher(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher getById(String id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ConstantMessage.NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public TeacherResponse getTeacherById(String id) {
        Teacher teacher = getById(id);
        return convertToTeacherResponse(teacher);
    }


    @Transactional(readOnly = true)
    @Override
    public List<TeacherResponse> getAllTeachers(String name) {
        if (name != null) {
            List<Teacher> teacherParameterList = teacherRepository.findAllByNameLike("%" + name + "%");
            return teacherParameterList.stream().map(this::convertToTeacherResponse).toList();
        }
        List<Teacher> teacherList = teacherRepository.findAll();
        return teacherList.stream().map(this::convertToTeacherResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TeacherResponse updateTeacher(UpdateTeacherRequest teacher) {
        validationUtil.validate(teacher);

        Teacher currentTeacher = getById(teacher.getId());

        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(currentTeacher.getUserAccount().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    ConstantMessage.USER_INVALID);
        }

        currentTeacher.setName(teacher.getName());
        currentTeacher.setMobilePhone(teacher.getMobilePhone());

        return convertToTeacherResponse(currentTeacher);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatusById(String id, Boolean status) {
        getById(id).setStatus(status);
        teacherRepository.updateStatus(id, status);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Teacher currentTeacher = getById(id);
        teacherRepository.delete(currentTeacher);
    }


    private TeacherResponse convertToTeacherResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .status(teacher.getStatus())
                .teacherEmail(teacher.getTeacherEmail())
                .userAccountId(teacher.getUserAccount().getId())
                .build();
    }
}
