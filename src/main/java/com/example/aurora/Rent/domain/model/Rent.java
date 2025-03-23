package com.example.aurora.Rent.domain.model;

import java.sql.Date;

import com.example.aurora.Client.domain.model.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne(mappedBy = "rent", cascade = CascadeType.ALL)
  private Client client;

  private Date startDate;

  private Date endDate;

  private int guestAmount;

  private int[] services;

  private RentStatus status;
}
