package com.justpz.springbootk8training.library.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventPublisher {

  private final ApplicationEventPublisher eventPublisher;

  public ReservationEventPublisher(
      ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public void publish(Reservation reservation){
    ReservationEvent reservationEvent = new ReservationEvent(this, reservation);
    eventPublisher.publishEvent(reservationEvent);
  }

}
