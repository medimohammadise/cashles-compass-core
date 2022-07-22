package com.ecanteen.domain;

import com.ecanteen.domain.enumeration.NotificationMethod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "userCredential")

public class UserCredential implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "userName")
    private String userName;


    @Column(name = "CreatedDate")
    private ZonedDateTime CreatedDate;


    public Long getId() {
        return id;
    }

    public UserCredential id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public UserCredential userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }




    public ZonedDateTime getCreatedDate() {
        return CreatedDate;
    }

    public UserCredential createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }


    public void setCreatedDate(ZonedDateTime createdDate) {
        CreatedDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCredential)) {
            return false;
        }
        return id != null && id.equals(((UserCredential) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


