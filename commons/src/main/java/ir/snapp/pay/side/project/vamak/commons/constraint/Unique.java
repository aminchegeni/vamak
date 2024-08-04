package ir.snapp.pay.side.project.vamak.commons.constraint;

import ir.snapp.pay.side.project.vamak.commons.constraint.validator.GeneralUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static ir.snapp.pay.side.project.vamak.commons.constraint.Unique.List;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = {GeneralUniqueValidator.class})
public @interface Unique {

    String message() default "{ir.snapp.pay.side.project.vamak.commons.constraint.Unique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String checker();

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Unique[] value();
    }

    @FunctionalInterface
    interface Checker<T> {

        Checker<?> NoOp = t -> false;

        boolean isExist(T t);

        static boolean not(boolean b) {
            return !b;
        }
    }
}
