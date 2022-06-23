package com.example.hello.Annotation;

import com.example.hello.Annotation.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CollectionValidator implements ConstraintValidator<Collection, java.util.Collection> {

    private int min;
    private int max;
    private String message;

    @Override
    public void initialize(Collection collection) {

        min = collection.min();
        max = collection.max();
        message = collection.message();

        ConstraintValidator.super.initialize(collection);
    }

    @Override
    public boolean isValid(java.util.Collection values, ConstraintValidatorContext context) {

        if(values.size() > max || values.size() < min){
            addConstraintViolation(
                    context,
                    String.format("최소 %d 이상 %d 이하여야 합니다.", min, max)
            );
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String msg) {
        //기본 메시지 비활성화
        context.disableDefaultConstraintViolation();
        //새로운 메시지 추가
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }
}