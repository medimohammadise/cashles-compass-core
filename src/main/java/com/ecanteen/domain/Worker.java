package com.ecanteen.domain;


import com.ecanteen.domain.enumeration.ROLE;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/***
 * A Worker
 */

@Entity
@Table(name = "worker")
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;


    @Column(name = "regNo")
    private String regNo;


    @Column(name = "comment")
    private String comment;


    @Column(name = "isApproved")
    private Boolean isApproved;


    @Column(name = "imageUrl")
    private String imageUrl;


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
    @OneToMany(cascade = CascadeType.ALL)
    private List<ActivationCode> activationCode = null;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserCredential> userCredential = null;


    public Long getId() {
        return id;
    }


    public Worker id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public Worker name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public Worker address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getRegNo() {
        return regNo;
    }

    public Worker regNo(String regNo) {
        this.setRegNo(regNo);
        return this;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }


    public String getComment() {
        return comment;
    }

    public Worker comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public boolean getIsApproved() {
        return isApproved;
    }

    public Worker isApproved(boolean isApproved) {
        this.setIsApproved(isApproved);
        return this;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public Worker imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public Worker createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }


    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public Worker modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }


    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public String getCreatedBy() {
        return CreatedBy;
    }

    public Worker createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Worker modifiedBy(String modifiedBy) {
        this.setModifiedBy(modifiedBy);
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public List<ActivationCode> getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(List<ActivationCode> activationCode) {
        this.activationCode = activationCode;
    }

    public List<UserCredential> getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(List<UserCredential> userCredential) {
        this.userCredential = userCredential;
    }


    public ROLE getRole() {
        return role;
    }

    public Worker role(String role) {
        this.setRole(ROLE.valueOf(role));
        return this;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Worker)) {
            return false;
        }
        return id != null && id.equals(((Worker) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
