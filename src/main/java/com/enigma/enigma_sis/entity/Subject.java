package com.enigma.enigma_sis.entity;

import com.enigma.enigma_sis.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
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

    @Column(name = "lesson_hours", nullable = false)
    private Integer lessonHours;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
