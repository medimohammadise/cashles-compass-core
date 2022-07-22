package com.ecanteen.service.dto;

 import java.io.Serializable;
import java.util.Objects;

public class UserCredentialDTO implements Serializable {
    private Long id;


     private String userName;


     private String CreatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCredentialDTO)) {
            return false;
        }

        UserCredentialDTO userCredentialDTO = (UserCredentialDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userCredentialDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
