package com.enigma.enigma_sis.entity;

import com.enigma.enigma_sis.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.ENROLLMENT)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "enrollment")
    private List<EnrollmentDetail> enrollmentDetails;

    @Column(name = "enrollment_term", nullable = false)
    private String enrollmentTerm;

    @Column(name = "study_load")
    private Integer studyLoad;
}
