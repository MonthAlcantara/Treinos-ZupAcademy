package io.github.monthalcantara.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteValorValidator implements ConstraintValidator<ExisteValor, String> {

    private EntityManager entityManager;
    private Class classe;
    private String campo;

    public ExisteValorValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(ExisteValor constraintAnnotation) {
        this.campo = constraintAnnotation.field();
        this.classe = constraintAnnotation.aClass();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) return true;
        return !entityManager
                .createQuery("Select 1 from " + classe.getSimpleName() + " where " + campo + " =:value")
                .setParameter("value", Long.valueOf(value))
                .getResultList()
                .isEmpty();

    }
}
