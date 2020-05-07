package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "classroom")
public class Classroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "classroom_id")
    private Long classroomId;

    @Column(unique = true)
    @NotNull
    private String classCode;

    @Column(columnDefinition = "50")
    private int capacity = 50;

    @Column(columnDefinition = "0")
    private boolean projector = false;

    @Column(columnDefinition = "0")
    private int plugs = 0;

    public Classroom() {
    }

    public Classroom(String classCode,int capacity,boolean projector,int plugs) {
        this.capacity=capacity;
        this.classCode=classCode;
        this.plugs=plugs;
        this.projector=projector;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public int getPlugs() {
        return plugs;
    }

    public void setPlugs(int plugs) {
        this.plugs = plugs;
    }
}
