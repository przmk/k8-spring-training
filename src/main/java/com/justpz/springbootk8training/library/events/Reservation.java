package com.justpz.springbootk8training.library.events;

import java.time.LocalDateTime;

public class Reservation {

  private LocalDateTime creationDate;
  private String description;

  public Reservation(LocalDateTime creationDate, String description) {
    this.creationDate = creationDate;
    this.description = description;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Reservation{" +
        "creationDate=" + creationDate +
        ", description='" + description + '\'' +
        '}';
  }
}
