package gfg.validation.controller.parameters;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NegativeOrZero;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
class ValidateGivenParametersByController {

  @GetMapping("/validatePathVariable/{id}")
  ResponseEntity<String> validatePathVariable(@PathVariable("id") @Min(5) int id) {
    return ResponseEntity.ok("valid");
  }

  @GetMapping("/validateRequestParameterWithMin")
  ResponseEntity<String> validateRequestParameterWithMin(@RequestParam("id") @Min(0) int id) {
    return ResponseEntity.ok("valid");
  }
  @GetMapping("/validateRequestParameterWithMax")
  ResponseEntity<String> validateRequestParameterWithMax(@RequestParam("id") @Max(5) int id) {
    return ResponseEntity.ok("valid");
  }
  @GetMapping("/validateRequestParameterWithEmailId")
  ResponseEntity<String> validateRequestParameterWithEmailId(@Email @RequestParam(name = "emailId")  String emailId) {
    return ResponseEntity.ok("valid");
  }
  @GetMapping("/validateRequestParameterWithPositive")
  ResponseEntity<String> validateRequestParameterWithPositive(@Positive @RequestParam(name = "id")  int id) {
    return ResponseEntity.ok("valid");
  }
  @GetMapping("/validateRequestParameterWithNegativeOrZero")
  ResponseEntity<String> validateRequestParameterWithNegativeOrZero(@NegativeOrZero @RequestParam(name = "id")  int id) {
    return ResponseEntity.ok("valid");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  String handleConstraintViolationException(ConstraintViolationException e) {
    return "Given input is not valid due to validation error: " + e.getMessage();
  }


}
