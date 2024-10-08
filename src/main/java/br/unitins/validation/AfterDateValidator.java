package br.unitins.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AfterDateValidator implements ConstraintValidator<AfterDate, LocalDate>   {
    private DateTimeFormatter pattern;
    private LocalDate referenceDate;

    @Override
    public void initialize(AfterDate constraintAnnotation) {
        this.pattern = DateTimeFormatter.ofPattern(constraintAnnotation.formatter());
        this.referenceDate = LocalDate.parse(constraintAnnotation.referenceDate(), this.pattern);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDateTime date = value.atStartOfDay();

        return value != null && date.isAfter(referenceDate.atStartOfDay());
    }

}
