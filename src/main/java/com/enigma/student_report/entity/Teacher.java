package com.enigma.student_report.entity;

import com.enigma.student_report.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.TEACHER)
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_id_sequence", allocationSize = 1)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subject_id", nullable = false)
    private String subjectId;

    @Column(name = "subject", nullable = false)
    private String subject;
}
