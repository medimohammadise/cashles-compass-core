package com.ecanteen.domain;

import com.ecanteen.domain.enumeration.NotificationMethod;
import com.ecanteen.domain.enumeration.NotificationStatus;

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
@Table(name = "notificationHistory")
public class NotificationHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private  Long id ;


    @Column(name ="date")
    private ZonedDateTime date;


    @Column(name ="status")
    private NotificationStatus status;

    @Column(name = "method")
    private NotificationMethod method;


    public Long getId() {
        return id;
    }

    public NotificationHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public NotificationHistory date(ZonedDateTime date) {
        this.setDate(date);
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public NotificationStatus getStatus() {
        return status;
    }


    public NotificationHistory status(NotificationStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public NotificationMethod getMethod() {
        return method;
    }


    public NotificationHistory method(NotificationMethod method) {
        this.setMethod(method);
        return this;
    }
    public void setMethod(NotificationMethod method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationHistory)) {
            return false;
        }
        return id != null && id.equals(((NotificationHistory) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
