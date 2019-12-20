package com.justpz.springbootk8training.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
  @GetMapping("reservation")
  String getReservations(){
    return "all";
  }

}
