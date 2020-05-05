package com.spe.ClassroomManagementSystem.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="login")
public class Login {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long loginId;

    @Column
    @NotNull
    private String userType;

    @Column
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String password;

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="foreignId")
    private Professor professor;

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="foreignId")
    private TA ta;

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="foreignId")
    private Committee committee;

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="foreignId")
    private Sac sac;

    
}
