package com.enigma.student_report.entity;

import com.enigma.student_report.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.STUDENT)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "study_group")
    private String studyGroup;

    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    @Column(name = "student_email")
    private String studentEmail;
}
