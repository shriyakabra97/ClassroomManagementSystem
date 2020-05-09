package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(
        name = "class_timing",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"day_of_the_week", "start_time", "end_time", "classroom_id"}
                )
)
public class ClassTiming implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long classTimingId;

    @Enumerated(EnumType.STRING)
    @Column(length = 9, name = "day_of_the_week")
    @NotNull
    private Day dayOfTheWeek;

    @Column(name = "start_time")
    @NotNull
    private Time startTime;

    @Column(name = "end_time")
    @NotNull
    private Time endTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;


//    @Column(name = "available")
//    @NotNull
//    boolean available = true;

    public ClassTiming() {
    }

    public ClassTiming(Day dayOfTheWeek,Time startTime,Time endTime,Classroom classroom) {
        this.classroom=classroom;
        this.dayOfTheWeek=dayOfTheWeek;
        this.startTime=startTime;
        this.endTime=endTime;
//        this.available=available;
    }

    public Long getClassTimingId() {
        return classTimingId;
    }

    public void setClassTimingId(Long classTimingId) {
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

//    public boolean isAvailable() {
//        return available;
//    }
//
//    public void setAvailable(boolean available) {
//        this.available = available;
//    }
}