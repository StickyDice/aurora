package com.aurora.aurora.dto.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePropertyDto {
  private String name;

  private String[] images;

  private String address;

  private String[] tags;

  private String[] guest_tags;

  private int price;

  private String[] description_articles;
}
