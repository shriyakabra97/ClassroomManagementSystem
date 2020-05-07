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
    private long classroomId;

    @Column
    @NotNull
    private String classCode;

    @Column(columnDefinition = "50")
    private long capacity = 50;

    @Column(columnDefinition = "0")
    private boolean projector = false;

    @Column(columnDefinition = "0")
    private long plugs = 0;

    public long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(long classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public long getPlugs() {
        return plugs;
    }

    public void setPlugs(long plugs) {
        this.plugs = plugs;
    }
}
