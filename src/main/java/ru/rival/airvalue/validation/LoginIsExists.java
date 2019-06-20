package ru.rival.airvalue.validation;

import ru.rival.airvalue.validation.impl.LoginIsExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginIsExistsValidator.class)
public @interface LoginIsExists {
    String message() default "{ru.rival.airvalue.constraint.LoginIsExists.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
