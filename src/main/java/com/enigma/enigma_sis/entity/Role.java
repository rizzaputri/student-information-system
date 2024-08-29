package com.enigma.enigma_sis.entity;

import com.enigma.enigma_sis.constant.ConstantTable;
import com.enigma.enigma_sis.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.USER_ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
