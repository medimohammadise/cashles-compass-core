package com.ecanteen.service.dto;


import com.ecanteen.domain.enumeration.ROLE;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 *  A DTO for {@Link Worker} entity.
 */
public class WorkerDTO implements Serializable {


    private Long id;

    private String name;


    private String address;


    private String regNo;



    private String comment;



    private Boolean isApproved;



    private String imageUrl;



    private ZonedDateTime createdDate;



    private ZonedDateTime modifiedDate;


    private String CreatedBy;



    private String modifiedBy;


    private ROLE role;


    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkerDTO)) {
            return false;
        }

        WorkerDTO workerDTO = (WorkerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
