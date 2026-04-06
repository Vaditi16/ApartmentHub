//package com.example.demo.model;
//
//public class Maintenance {
//
//}
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate dueDate;

    private String status; // PAID / UNPAID

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    public Maintenance() {}

    public Maintenance(Double amount, LocalDate dueDate, String status, Flat flat) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.flat = flat;
    }

    // Getters & Setters

    public Long getId() { return id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Flat getFlat() { return flat; }
    public void setFlat(Flat flat) { this.flat = flat; }
}