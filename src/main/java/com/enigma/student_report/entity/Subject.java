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
@Table(name = ConstantTable.SUBJECT)
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_id_sequence", allocationSize = 1)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lessons_hours", nullable = false)
    private Integer lessonsHours;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
