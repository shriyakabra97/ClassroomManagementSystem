package com.spe.ClassroomManagementSystem.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ta")
public class TA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String taName;

    @Column(unique = true)
    @NotNull
    private String taEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;
}
