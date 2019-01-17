package com.andrew.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author andrew
 */
@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping("/error")
  public ResponseEntity<String> handleError(HttpServletRequest request) {
    final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      final int statusCode = Integer.valueOf(status.toString());
      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("We couldn't find the page you're looking for :(");
      }

      if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. We're on it. :(");
      }
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @Override
  public String getErrorPath() {

    return "/error";
  }
}