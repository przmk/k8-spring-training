package com.justpz.springbootk8training.library.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;

class ReservationInfo {

  @JsonProperty("book_name")
  private String bookName;

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getBookName() {
    return bookName;
  }
}
