package com.enigma.student_report.service.impl;

import com.enigma.student_report.constant.ConstantMessage;
import com.enigma.student_report.dto.request.NewTeacherRequest;
import com.enigma.student_report.entity.Teacher;
import com.enigma.student_report.repository.TeacherRepository;
import com.enigma.student_report.service.TeacherService;
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
