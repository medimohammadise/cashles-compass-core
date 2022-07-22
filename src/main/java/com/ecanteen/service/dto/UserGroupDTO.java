package com.ecanteen.service.dto;

 import java.io.Serializable;
import java.util.Objects;

public class UserGroupDTO implements Serializable {
    private Long id;


    private String code;


    private String name;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserGroupDTO)) {
            return false;
        }

        UserGroupDTO userGroupDTO = (UserGroupDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userGroupDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
