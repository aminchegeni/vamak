package ir.snapp.pay.side.project.vamak.commons.constraint;

import ir.snapp.pay.side.project.vamak.commons.constraint.validator.GeneralExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static ir.snapp.pay.side.project.vamak.commons.constraint.Exist.List;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = {GeneralExistValidator.class})
public @interface Exist {

    String message() default "{ir.snapp.pay.side.project.vamak.commons.constraint.Exist.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean revert() default false;

    String checker();

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Exist[] value();
    }

    @FunctionalInterface
    interface Checker<T> {

        Checker<?> EXIST = t -> true;

        Checker<?> NOT_EXIST = t -> false;

        boolean isExist(T t);

        static boolean not(boolean b) {
            return !b;
        }
    }
}
