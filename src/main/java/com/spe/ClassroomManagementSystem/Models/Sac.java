package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sac")
public class Sac {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sacId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String sacName;

    @Column(unique = true)
    @NotNull
    private String sacEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;
}
