package com.justpz.springbootk8training.library.events;

import org.springframework.context.ApplicationEvent;

public class ReservationEvent extends ApplicationEvent {
  private Reservation reservation;

  public ReservationEvent(Object source,
      Reservation reservation) {
    super(source);
    this.reservation = reservation;
  }

  public Reservation getReservation() {
    return reservation;
  }
}
