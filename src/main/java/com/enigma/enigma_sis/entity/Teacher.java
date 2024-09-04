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
@Table(name = ConstantTable.TEACHER)
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "teacher_email")
    private String teacherEmail;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
}
