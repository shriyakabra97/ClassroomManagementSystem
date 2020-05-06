package com.spe.ClassroomManagementSystem.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long professorId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String professorName;

    @Column(unique = true)
    @NotNull
    private String professorEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;
    //primary key of login becomes foreign key here


    public Professor() {
    }

    public Professor(@NotNull String userName, @NotNull String professorName, @NotNull String professorEmail) {
        this.userName = userName;
        this.professorName = professorName;
        this.professorEmail = professorEmail;
    }

    public Professor(@NotNull String userName, @NotNull String professorName, @NotNull String professorEmail, Login foreignId) {
        this.userName = userName;
        this.professorName = professorName;
        this.professorEmail = professorEmail;
        this.foreignId = foreignId;
    }

//    @Override
//    public String toString() {
//        return "Professor{" +
//                "professorId=" + professorId +
//                ", userName='" + userName + '\'' +
//                ", professorName='" + professorName + '\'' +
//                ", professorEmail='" + professorEmail + '\'' +
//                ", foreignId=" + foreignId +
//                '}';
//    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public Login getForeignId() {
        return foreignId;
    }

    public void setForeignId(Login foreignId) {
        this.foreignId = foreignId;
    }
}
