package br.unitins.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AfterDateValidator.class)
public @interface AfterDate {
    String message() default "A data deve ser depois à data de referência";
    String formatter() default "yyyy-MM-dd";
    String referenceDate();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
