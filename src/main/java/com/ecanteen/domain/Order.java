package com.ecanteen.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Order
 */

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;
    @Column(name = "orderNumber")
    private String orderNumber;
    @Column(name = "orderCode")
    private String orderCode;
    @Column(name = "createdDate")
    private ZonedDateTime createdDate;
    @Column(name = "modifiedDate")
    private ZonedDateTime modifiedDate;





    public Long getId() {
        return id;
    }

    public Order id(Long id) {
        this.setId(id);
        return this;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Order orderNumber(String orderNumber) {
        this.setOrderNumber(orderNumber);
        return this;

    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public Order orderCode(String orderCode) {
        this.setOrderCode(orderCode);
        return this;

    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public Order createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;

    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public Order modifiedDate(ZonedDateTime modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;

    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
