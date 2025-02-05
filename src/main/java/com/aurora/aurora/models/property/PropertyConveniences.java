package com.aurora.aurora.models.property;

public enum PropertyConveniences {
  TV("TV"),
  WIFI("WIFI"),
  KITCHEN("KITCHEN"),
  OFFICE("OFFICE"),
  FIREPLACE("FIREPLACE"),
  PHONE("PHONE"),
  REFRIGATOR("REFRIGATOR"),
  TEAPOT("TEAPOT"),
  COFFEE_MAKER("COFFEE_MAKER"),
  DISHWASHER("DISHWASHER"),
  OVEN("OVEN"),
  MICRO_WAVE("MICRO_WAVE"),
  MEAL("MEAL"),
  IRON("IRON"),
  DRYER("DRYER"),
  SHOWER("SHOWER");

  private final String value;

  PropertyConveniences(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
