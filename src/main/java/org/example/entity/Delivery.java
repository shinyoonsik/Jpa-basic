package org.example.entity;


import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
public class Delivery extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String address;
    private String status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
