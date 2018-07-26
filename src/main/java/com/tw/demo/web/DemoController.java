package com.tw.demo.web;

import com.tw.demo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<User> hello() {
        User user = new User("U001", "Lily");
        return ResponseEntity.ok(user);
    }
}
