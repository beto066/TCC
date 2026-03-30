package br.unitins.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AfterDateValidator implements ConstraintValidator<AfterDate, OffsetDateTime> {
    private DateTimeFormatter referencePattern;
    private LocalDate referenceDate;

    @Override
    public void initialize(AfterDate constraintAnnotation) {
        this.referencePattern = DateTimeFormatter.ofPattern(constraintAnnotation.referenceFormatter());
        this.referenceDate = LocalDate.parse(constraintAnnotation.referenceDate(), this.referencePattern);
    }

    @Override
    public boolean isValid(OffsetDateTime value, ConstraintValidatorContext context) {
        LocalDateTime date = null;

        if (value != null) {
            date = value.toLocalDateTime();
        }

        return value != null && date.isAfter(referenceDate.atStartOfDay());
    }
}
