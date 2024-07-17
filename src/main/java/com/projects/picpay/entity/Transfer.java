package com.projects.picpay.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "wallet_sender_id")
    private Wallet sender;

    @ManyToOne
    @JoinColumn(name = "wallet_reciver_id")
    private Wallet reciver;

    @Column(name = "value")
    private BigDecimal value;

    public Transfer() {
    }

    public Transfer(Wallet sender, Wallet reciver, BigDecimal value) {
        this.sender = sender;
        this.reciver = reciver;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Wallet getSender() {
        return sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getReciver() {
        return reciver;
    }

    public void setReciver(Wallet reciver) {
        this.reciver = reciver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
