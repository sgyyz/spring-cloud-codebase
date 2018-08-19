package com.troy.codebase.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloResource {

  @GetMapping("/{name}")
  public String sayHello(@PathVariable String name) {
    return "Hello, " + name;
  }
}
