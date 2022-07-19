package com.ecanteen.domain;

import com.ecanteen.domain.enumeration.ROLE;

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


/***
 * A Parent
 */

@Entity
@Table(name = "parent")
public class Parent implements Serializable {
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Parent fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Parent email(String email) {
        this.setEmail(email);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Parent phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Parent address(String address) {
        this.setAddress(address);
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Parent imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Parent enabled(Boolean enabled) {
        this.setEnabled(enabled);
        return this;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Parent phoneVerified(Boolean phoneVerified) {
        this.setPhoneVerified(phoneVerified);
        return this;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Parent emailVerified(Boolean emailVerified) {
        this.setEmailVerified(emailVerified);
        return this;
    }


    public String getKkUesId() {
        return KkUesId;
    }

    public void setKkUesId(String kkUesId) {
        KkUesId = kkUesId;
    }

    public Parent kkUesId(String kkUesId) {
        this.setKkUesId(kkUesId);
        return this;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Parent createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Parent modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public Parent createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Parent modifiedBy(String modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public Parent role(String role) {
        this.setRole(ROLE.valueOf(role));
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parent)) {
            return false;
        }
        return id != null && id.equals(((Parent) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}


