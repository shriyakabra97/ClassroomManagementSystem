package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "class_timing")
public class ClassTiming implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long classTimingId;

    @Enumerated(EnumType.STRING)
    @Column(length = 9)
    @NotNull
    private Day dayOfTheWeek;

    @Column
    @NotNull
    private Time startTime;

    @Column
    @NotNull
    private Time endTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    public long getClassTimingId() {
        return classTimingId;
    }

    public void setClassTimingId(long classTimingId) {
        this.classTimingId = classTimingId;
    }

    public Day getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(Day dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}