package org.pokemonrun.jaccountauth.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "studentID")
public class UserJAccount implements Serializable {
    private Long studentID;
    private String token;

    public UserJAccount() {
        // empty
    }

    @Id
    @Column(name = "studentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserJAccount that = (UserJAccount) o;

        if (studentID != that.studentID) return false;
        if (!Objects.equals(token, that.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentID.hashCode();
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
