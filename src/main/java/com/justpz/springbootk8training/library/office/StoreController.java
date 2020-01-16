package com.justpz.springbootk8training.library.office;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StoreController {

  @GetMapping("books")
  public List<String> getBooks() {
    return List.of("Book 1", "Book 2");
  }
}
