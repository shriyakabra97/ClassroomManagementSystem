package com.spe.ClassroomManagementSystem.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="ta")
public class TA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taId;

    @Column(unique = true)
    @NotNull
    private String userName;

    @Column
    @NotNull
    private String taName;

    @Column(unique = true)
    @NotNull
    private String taEmail;

    @OneToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="foreignId" ,nullable=false)
    private Login foreignId;

    public TA(){

    }
    public TA(@NotNull String userName, @NotNull String taName, @NotNull String taEmail) {
        this.userName = userName;
        this.taName = taName;
        this.taEmail = taEmail;
    }

    public TA(@NotNull String userName, @NotNull String taName, @NotNull String taEmail, Login foreignId) {
        this.userName = userName;
        this.taName = taName;
        this.taEmail = taEmail;
        this.foreignId = foreignId;
    }


//    @Override
//    public String toString() {
//        return "TA{" +
//                "taId=" + taId +
//                ", userName='" + userName + '\'' +
//                ", taName='" + taName + '\'' +
//                ", taEmail='" + taEmail + '\'' +
//                ", foreignId=" + foreignId +
//                '}';
//    }

    public long getTaId() {
        return taId;
    }

    public void setTaId(long taId) {
        this.taId = taId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaName() {
        return taName;
    }

    public void setTaName(String taName) {
        this.taName = taName;
    }

    public String getTaEmail() {
        return taEmail;
    }

    public void setTaEmail(String taEmail) {
        this.taEmail = taEmail;
    }

    public Login getForeignId() {
        return foreignId;
    }

    public void setForeignId(Login foreignId) {
        this.foreignId = foreignId;
    }
}
