package ir.snapp.pay.side.project.vamak.commons.constraint.validator;

import ir.snapp.pay.side.project.vamak.commons.constraint.Exist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.Objects;

import static ir.snapp.pay.side.project.vamak.commons.constraint.Exist.Checker.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GeneralExistValidator implements ApplicationContextAware, ConstraintValidator<Exist, Object> {

    private String name;
    private boolean revert;
    private Map<String, Exist.Checker> checkers;

    @Override
    public void initialize(Exist constraintAnnotation) {
        name = constraintAnnotation.checker();
        revert = constraintAnnotation.revert();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        var checker = checkers.getOrDefault(name, revert ? NOT_EXIST : EXIST);
        if (revert) {
            return not(checker.isExist(value));
        } else {
            return checker.isExist(value);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        checkers = applicationContext.getBeansOfType(Exist.Checker.class);
    }
}
