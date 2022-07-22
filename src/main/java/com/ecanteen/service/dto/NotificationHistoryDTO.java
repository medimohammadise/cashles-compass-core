package com.ecanteen.service.dto;

import com.ecanteen.domain.enumeration.NotificationMethod;
import com.ecanteen.domain.enumeration.NotificationStatus;

 import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class NotificationHistoryDTO implements Serializable {

    private  Long id ;


     private ZonedDateTime date;


     private NotificationStatus status;

     private NotificationMethod method;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public NotificationMethod getMethod() {
        return method;
    }

    public void setMethod(NotificationMethod method) {
        this.method = method;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationHistoryDTO)) {
            return false;
        }

        NotificationHistoryDTO notificationHistoryDTO = (NotificationHistoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, notificationHistoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
