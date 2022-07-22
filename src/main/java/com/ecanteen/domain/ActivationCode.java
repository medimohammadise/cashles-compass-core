package com.ecanteen.domain;

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
@Table(name = "activationCode")
public class ActivationCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private  Long id ;


    @Column(name ="code")
    private String code;


    @Column(name ="expiryTime")
    private String expiryTime;

    @Column(name = "createdDate")
    private ZonedDateTime createdDate;


    @Column(name = "createdBy")
    private String createdBy;


    public Long getId() {
        return id;
    }


    public ActivationCode createdDate(Long id) {
        this.setId(id);
        return this;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public ActivationCode code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public ActivationCode expiryTime(String expiryTime) {
        this.setExpiryTime(expiryTime);
        return this;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }


    public ActivationCode createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ActivationCode createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivationCode)) {
            return false;
        }
        return id != null && id.equals(((ActivationCode) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
