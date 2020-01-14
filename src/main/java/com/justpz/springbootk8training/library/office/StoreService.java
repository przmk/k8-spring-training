package com.justpz.springbootk8training.library.office;

import com.justpz.springbootk8training.library.events.Reservation;
import com.justpz.springbootk8training.library.events.ReservationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
class StoreService {

  private static final Logger log = LoggerFactory.getLogger(StoreService.class);

  void create(Reservation reservation) {
    log.info("Reservation of book {}, id [{}]", reservation, Thread.currentThread().getId());
  }

  @EventListener(condition = "#event.reservation.description eq 'success'")
  public void logReservation(ReservationEvent event) {
    log.info("Success {}, id [{}]", event.getReservation(), Thread.currentThread().getId());
  }

  @Async
  @EventListener(condition = "#event.reservation.description eq 'success'")
  public void logReservation2(ReservationEvent event) {
    try {
      Thread.sleep(10_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("Success2 {}, id [{}]", event.getReservation(), Thread.currentThread().getId());
  }
}
