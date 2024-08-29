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
@Table(name = ConstantTable.TEACHER)
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "teacher_email", nullable = false, unique = true)
    private String teacherEmail;
}
