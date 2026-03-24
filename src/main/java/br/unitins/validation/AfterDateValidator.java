package br.unitins.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AfterDateValidator implements ConstraintValidator<AfterDate, String> {
    private DateTimeFormatter referencePattern;
    private DateTimeFormatter payloadPattern;
    private LocalDate referenceDate;

    @Override
    public void initialize(AfterDate constraintAnnotation) {
        this.referencePattern = DateTimeFormatter.ofPattern(constraintAnnotation.referenceFormatter());
        this.payloadPattern = DateTimeFormatter.ofPattern(constraintAnnotation.payloadFormatter());
        this.referenceDate = LocalDate.parse(constraintAnnotation.referenceDate(), this.referencePattern);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        LocalDateTime date = null;

        if (value != null) {
            date = LocalDateTime.parse(value, payloadPattern);
        }

        return value != null && date.isAfter(referenceDate.atStartOfDay());
    }
}
