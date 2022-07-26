package com.ecanteen.service.dto;


import com.ecanteen.domain.Parent;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AccountDTO implements Serializable {

    private Long id;


    private String accountNumber;


    private String AccountName;

    private ZonedDateTime createdDate;


    private ZonedDateTime modifiedDate;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountDTO)) {
            return false;
        }

        AccountDTO accountDTO = (AccountDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, accountDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }


}
