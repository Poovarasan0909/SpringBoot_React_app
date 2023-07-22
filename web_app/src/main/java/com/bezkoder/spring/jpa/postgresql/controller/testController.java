package com.bezkoder.spring.jpa.postgresql.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api2")
@Transactional(rollbackFor = Exception.class)
public class testController {

//    @PostMapping("/error")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String internalServerError() {
//        return "response code: 500";
//       }

//    @RequestMapping("/error")
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public void internalServerErrors() {
//
//    }

//    @RequestMapping("/error")
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> internalServerError() {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("response code: 500");
//    }

    @RequestMapping("/error")
    public ResponseEntity<Object> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @RequestMapping("/search")
    public ResponseEntity<String> search(@RequestParam("query") String query) {
        throw new RuntimeException("Internal Server Error");
    }

}
