package br.com.restful.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restful.exception.UnsupportedMathOperationException;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{num1}/{num2}")
    public Double sum(
            @PathVariable("num1") String num1,
            @PathVariable("num2") String num2) {
        if (!isNumeric(num1) || !isNumeric(num2)) {
            throw new UnsupportedMathOperationException("Por favor digite valores numéricos.");
        }
        
        return convertToDouble(num1) + convertToDouble(num2);

    }

    private Double convertToDouble(String num) {

        if (num == null || num.isEmpty()) {
            throw new UnsupportedMathOperationException("Por favor digite valores numéricos.");
        }
        return Double.valueOf(num.replace(",", "."));
    }

    private boolean isNumeric(String num) {

        if (num == null || num.isEmpty()) {
            return false;
        }
        String number = num.replace(",", ".");
        
        return (number.matches("[-+]?\\d*\\.?\\d+"));
    }
}
