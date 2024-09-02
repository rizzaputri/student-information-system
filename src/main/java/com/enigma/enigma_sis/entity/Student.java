package com.enigma.enigma_sis.entity;

import com.enigma.enigma_sis.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "study_group")
    private String studyGroup;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "student_email")
    private String studentEmail;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
}
