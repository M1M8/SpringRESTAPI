package com.sparta.mm.springrestapi.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payment", schema = "sakila")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id")
    private Integer paymentId;
    @Basic
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Integer customerId;
    @Basic
    @Column(name = "staff_id", insertable = false, updatable = false)
    private Integer staffId;
    @Basic
    @Column(name = "rental_id", insertable = false, updatable = false)
    private Integer rentalId;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Basic
    @Column(name = "last_update")
    private LocalDate lastUpdate;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private CustomerEntity customerByCustomerId;
    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "rental_id")
    private RentalEntity rentalByRentalId;
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false)
    private StaffEntity staffByStaffId;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Object getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(paymentId, that.paymentId) && Objects.equals(customerId, that.customerId) && Objects.equals(staffId, that.staffId) && Objects.equals(rentalId, that.rentalId) && Objects.equals(amount, that.amount) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, customerId, staffId, rentalId, amount, paymentDate, lastUpdate);
    }

    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    public RentalEntity getRentalByRentalId() {
        return rentalByRentalId;
    }

    public void setRentalByRentalId(RentalEntity rentalByRentalId) {
        this.rentalByRentalId = rentalByRentalId;
    }

    public StaffEntity getStaffByStaffId() {
        return staffByStaffId;
    }

    public void setStaffByStaffId(StaffEntity staffByStaffId) {
        this.staffByStaffId = staffByStaffId;
    }
}
