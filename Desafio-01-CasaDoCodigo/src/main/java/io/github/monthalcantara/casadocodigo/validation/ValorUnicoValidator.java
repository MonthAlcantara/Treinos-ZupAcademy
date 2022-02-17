package io.github.monthalcantara.casadocodigo.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> aClass;
    private String field;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.aClass = constraintAnnotation.aClass();
        this.field = constraintAnnotation.field();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return entityManager
                .createQuery("Select 1 from " + aClass.getSimpleName() + " where " + field + " =:value")
                .setParameter("value", value)
                .getResultList()
                .isEmpty();

    }
}
