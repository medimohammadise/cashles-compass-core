package com.ecanteen.service.dto;

 import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ActivationCodeDTO implements Serializable {

    private  Long id ;


     private String code;


     private String expiryTime;

     private ZonedDateTime createdDate;


     private String createdBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActivationCodeDTO)) {
            return false;
        }

        ActivationCodeDTO activationCodeDTO = (ActivationCodeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, activationCodeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
