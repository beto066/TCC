package br.unitins.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BeforeDateValidator implements ConstraintValidator<BeforeDate, LocalDate>  {
    private DateTimeFormatter pattern;
    private LocalDate referenceDate;

    @Override
    public void initialize(BeforeDate constraintAnnotation) {
        this.pattern = DateTimeFormatter.ofPattern(constraintAnnotation.formatter());
        this.referenceDate = LocalDate.parse(constraintAnnotation.referenceDate(), this.pattern);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDateTime date = value.atStartOfDay();

        return value != null && date.isBefore(referenceDate.atStartOfDay());
    }
}
