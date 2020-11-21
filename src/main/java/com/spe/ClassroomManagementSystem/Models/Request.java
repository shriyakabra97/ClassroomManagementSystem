package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "request")
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id", nullable = false)
    private Login requestor;

    @Column
    @NotNull
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(length = 9)
    @NotNull
    private RequestStatus requestStatus;

    @Column(name = "class_request_date")
    @NotNull
    private Date classRequestDate;

    @Column
    @NotNull
    private Time startTime;

    @Column
    @NotNull
    private Time endTime;

    @Column
    private String comment = "";

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @Column
    private boolean projector = false;

    @Column
    private long plugs = 0;

    @Column
    private boolean cleaningRequired = false;
    public  Request(){

    }

    public Request(Login requestor, @NotNull String purpose,
                   @NotNull RequestStatus requestStatus, @NotNull Date classRequestDate,
                   @NotNull Time startTime, @NotNull Time endTime,
                   String comment, Classroom classroom,
                   boolean projector, long plugs,
                   boolean cleaningRequired) {
        this.requestor = requestor;
        this.purpose = purpose;
        this.requestStatus = requestStatus;
        this.classRequestDate = classRequestDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
        this.classroom = classroom;
        this.projector = projector;
        this.plugs = plugs;
        this.cleaningRequired = cleaningRequired;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public Login getRequestor() {
        return requestor;
    }

    public void setRequestor(Login requestor) {
        this.requestor = requestor;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getClassRequestDate() {
        return classRequestDate;
    }

    public void setClassRequestDate(Date classRequestDate) {
        this.classRequestDate = classRequestDate;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
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

    public boolean isCleaningRequired() {
        return cleaningRequired;
    }

    public void setCleaningRequired(boolean cleaningRequired) {
        this.cleaningRequired = cleaningRequired;
    }
}