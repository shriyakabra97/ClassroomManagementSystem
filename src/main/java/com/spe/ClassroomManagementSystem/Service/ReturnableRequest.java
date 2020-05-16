package com.spe.ClassroomManagementSystem.Service;

import com.spe.ClassroomManagementSystem.Models.Request;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class ReturnableRequest implements Serializable {
//    private Request embeddedRequest;
    Time startTime;
    Time endTime;
    Date classRequestDate;
    String comment;
    long plugs;
    String projector;
    String  cleaningRequired;
    private String classCode;
    private String rName;
    String purpose;
    long loginId;
    long classroomId;
    long requestId;
    int index;


    public ReturnableRequest(
            Time startTime, Time endTime, Date classRequestDate,
            String comment, long plugs, String  projector, String  cleaningRequired,
            String classCode, String rName, String purpose,
            long loginId, long classroomId, long requestId,
            int index
            )
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.classRequestDate = classRequestDate;
        this.comment = comment;
        this.plugs = plugs;
        this.projector = projector;
        this.cleaningRequired = cleaningRequired;
        this.classCode = classCode;
        this.rName = rName;
        this.purpose = purpose;
        this.loginId = loginId;
        this.classroomId = classroomId;
        this.requestId = requestId;
        this.index = index;
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

    public Date getClassRequestDate() {
        return classRequestDate;
    }

    public void setClassRequestDate(Date classRequestDate) {
        this.classRequestDate = classRequestDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getPlugs() {
        return plugs;
    }

    public void setPlugs(long plugs) {
        this.plugs = plugs;
    }

    public String getProjector() {
        return projector;
    }

    public void setProjector(String projector) {
        this.projector = projector;
    }

    public String getCleaningRequired() {
        return cleaningRequired;
    }

    public void setCleaningRequired(String cleaningRequired) {
        this.cleaningRequired = cleaningRequired;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    public long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(long classroomId) {
        this.classroomId = classroomId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
