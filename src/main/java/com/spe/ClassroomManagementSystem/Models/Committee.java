package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="committee")
public class Committee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long committeeId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String committeeName;

    @Column(unique = true)
    @NotNull
    private String committeeEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;
}
