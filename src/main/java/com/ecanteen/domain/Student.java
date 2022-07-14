package com.ecanteen.domain;

import com.ecanteen.domain.enumeration.ROLE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

import java.util.Objects;

/***
 * A Student
 */


@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fullName")
    private String fullName;


    @Column(name = "email")
    private String email;


    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "isEnabled")
    private Boolean isEnabled;


    @Column(name = "phoneVerified")
    private Boolean phoneVerified;


    @Column(name = "emailVerified")
    private Boolean emailVerified;

    @Column(name = "KkUseId")
    private String KkUesId;


    @Column(name = "createdDate")
    private ZonedDateTime createdDate;


    @Column(name = "modifiedDate")
    private ZonedDateTime modifiedDate;

    @Column(name = "createdBy")
    private String CreatedBy;


    @Column(name = "modifiedBy")
    private String modifiedBy;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ROLE role;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(fullName, student.fullName) && Objects.equals(email, student.email) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(address, student.address) && Objects.equals(imageUrl, student.imageUrl) && Objects.equals(isEnabled, student.isEnabled) && Objects.equals(phoneVerified, student.phoneVerified) && Objects.equals(emailVerified, student.emailVerified) && Objects.equals(KkUesId, student.KkUesId) && Objects.equals(createdDate, student.createdDate) && Objects.equals(modifiedDate, student.modifiedDate) && Objects.equals(CreatedBy, student.CreatedBy) && Objects.equals(modifiedBy, student.modifiedBy) && role == student.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, phoneNumber, address, imageUrl, isEnabled, phoneVerified, emailVerified, KkUesId, createdDate, modifiedDate, CreatedBy, modifiedBy, role);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}

