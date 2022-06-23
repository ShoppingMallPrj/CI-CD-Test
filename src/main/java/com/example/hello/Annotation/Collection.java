package com.example.hello.Annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CollectionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Collection {

    public int max() default 1000;
    public int min() default 0;
    String message() default "배열 범위 오류";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
