package com.justpz.springbootk8training.library.reservation;

import com.justpz.springbootk8training.library.events.Reservation;
import com.justpz.springbootk8training.library.events.ReservationEventPublisher;
import java.time.Clock;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ReservationController {

  private static final Logger log = LoggerFactory.getLogger(ReservationController.class);
  private final ReservationEventPublisher eventPublisher;
  private final Clock clock;

  ReservationController(
      ReservationEventPublisher eventPublisher, Clock clock) {
    this.eventPublisher = eventPublisher;
    this.clock = clock;
  }

  @GetMapping("")
  public void check() {
  }

  @GetMapping("reservation")
  public String getReservations() {
    return "all";
  }

  @PostMapping("reservation")
  public void makeReservation(@RequestBody ReservationInfo reservationInfo) {
    log.info("started {}", Thread.currentThread().getId());
    eventPublisher
        .publish(new Reservation(LocalDateTime.now(clock), reservationInfo.getBookName()));
    log.info("ended {}", Thread.currentThread().getId());
  }

}
