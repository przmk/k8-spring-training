package com.justpz.springbootk8training.library.office;

import com.justpz.springbootk8training.library.events.ReservationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class ReservationIncomeListener implements ApplicationListener<ReservationEvent> {
  private final StoreService storeService;

  ReservationIncomeListener(
      StoreService storeService) {
    this.storeService = storeService;
  }

  @Override
  public void onApplicationEvent(ReservationEvent reservationEvent) {
    storeService.create(reservationEvent.getReservation());
  }
}
