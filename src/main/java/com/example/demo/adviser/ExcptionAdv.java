package com.example.demo.adviser;

import com.example.demo.model.DemoResponse;
import com.example.demo.model.DemoResponseCode;
import com.example.demo.model.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

import static java.util.stream.Collectors.joining;

@ControllerAdvice(annotations = RestController.class)
public class ExcptionAdv {
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public DemoResponse ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        // String error = ex.getConstraintViolations().stream().map(e -> e.getMessageTemplate()).collect(joining());
        return new DemoResponse(DemoResponseCode.CODE_112.getCode(),DemoResponseCode.CODE_112.getMessage(),null);

    }

    @ResponseBody
    @ExceptionHandler(value = NotFoundException.class)
    public DemoResponse notFoundExcptionHandler(NotFoundException e){
        return new DemoResponse(DemoResponseCode.CODE_114.getCode(),DemoResponseCode.CODE_114.getMessage(),null);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public DemoResponse otherExcptionHandler(Exception e){
        return new DemoResponse(DemoResponseCode.CODE_999.getCode(),DemoResponseCode.CODE_999.getMessage(),null);
    }

}
