package com.spe.ClassroomManagementSystem.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="committee")
public class Committee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long committeeId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String committeeName;

    @Column(unique = true)
    @NotNull
    private String committeeEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;


    public Committee() {
    }

    public Committee(@NotNull String userName, @NotNull String committeeName, @NotNull String committeeEmail, Login foreignId) {
        this.userName = userName;
        this.committeeName = committeeName;
        this.committeeEmail = committeeEmail;
        this.foreignId = foreignId;
    }

    public Committee(@NotNull String userName, @NotNull String committeeName, @NotNull String committeeEmail) {
        this.userName = userName;
        this.committeeName = committeeName;
        this.committeeEmail = committeeEmail;
    }

//    @Override
//    public String toString() {
//        return "Committee{" +
//                "committeeId=" + committeeId +
//                ", userName='" + userName + '\'' +
//                ", committeeName='" + committeeName + '\'' +
//                ", committeeEmail='" + committeeEmail + '\'' +
//                ", foreignId=" + foreignId +
//                '}';
//    }

    public long getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(long committeeId) {
        this.committeeId = committeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getCommitteeEmail() {
        return committeeEmail;
    }

    public void setCommitteeEmail(String committeeEmail) {
        this.committeeEmail = committeeEmail;
    }

    public Login getForeignId() {
        return foreignId;
    }

    public void setForeignId(Login foreignId) {
        this.foreignId = foreignId;
    }
}
