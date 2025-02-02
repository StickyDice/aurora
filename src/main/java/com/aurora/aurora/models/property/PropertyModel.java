package com.aurora.aurora.models.property;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Property")
public class PropertyModel {
  /* -------------------------------------------------------------------------- */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /* -------------------------------------------------------------------------- */
  private String name;

  /* -------------------------------------------------------------------------- */
  private String[] images;

  /* -------------------------------------------------------------------------- */
  
  private String address;

  /* -------------------------------------------------------------------------- */
  private String[] tags;

  /* -------------------------------------------------------------------------- */
  private String[] guest_tags;

  /* -------------------------------------------------------------------------- */
  private int price;

  /* -------------------------------------------------------------------------- */
  private int reservation_price;

  /* -------------------------------------------------------------------------- */
  private String[] description_articles;

  /* -------------------------------------------------------------------------- */

}
