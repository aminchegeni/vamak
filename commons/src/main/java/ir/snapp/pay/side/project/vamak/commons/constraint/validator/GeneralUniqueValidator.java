package ir.snapp.pay.side.project.vamak.commons.constraint.validator;

import ir.snapp.pay.side.project.vamak.commons.constraint.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.Objects;

import static ir.snapp.pay.side.project.vamak.commons.constraint.Unique.Checker.NoOp;
import static ir.snapp.pay.side.project.vamak.commons.constraint.Unique.Checker.not;

public class GeneralUniqueValidator implements ApplicationContextAware, ConstraintValidator<Unique, Object> {

    private String name;
    private Map<String, Unique.Checker> checkers;

    @Override
    public void initialize(Unique constraintAnnotation) {
        name = constraintAnnotation.checker();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        var checker = checkers.getOrDefault(name, NoOp);
        return not(checker.isExist(value));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        checkers = applicationContext.getBeansOfType(Unique.Checker.class);
    }
}
