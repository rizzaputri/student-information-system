package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.NewTeacherRequest;
import com.enigma.enigma_sis.entity.Teacher;
import com.enigma.enigma_sis.repository.TeacherRepository;
import com.enigma.enigma_sis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher inputTeacher(NewTeacherRequest newTeacher) {
        Teacher teacher = Teacher
                .builder()
                .name(newTeacher.getName())
                .teacherEmail(newTeacher.getTeacherEmail())
                .build();

        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ConstantMessage.NOT_FOUND)
        );
    }

    @Override
    public List<Teacher> getAllTeachers(String name) {
        if (name != null) {
            return teacherRepository.findAllByNameLike("%" + name + "%");
        }
        return teacherRepository.findAll();
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        getTeacherById(teacher.getId());
        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        Teacher currentTeacher = getTeacherById(id);
        teacherRepository.delete(currentTeacher);
    }
}
