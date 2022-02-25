package io.github.monthalcantara.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, String> {

    private final EntityManager manager;
    private String field;
    private Class classe;

    public ValorUnicoValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.classe = constraintAnnotation.aClass();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return manager.createQuery("Select 1 from " + classe.getSimpleName() + " where " + field + " =:value")
                .setParameter("value", value)
                .getResultList()
                .isEmpty();
    }
}
