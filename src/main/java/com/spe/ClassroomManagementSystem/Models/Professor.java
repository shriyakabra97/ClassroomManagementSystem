package com.spe.ClassroomManagementSystem.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long professorId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String professorName;

    @Column(unique = true)
    @NotNull
    private String professorEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;

}
