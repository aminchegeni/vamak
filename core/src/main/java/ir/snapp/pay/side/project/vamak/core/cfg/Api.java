package ir.snapp.pay.side.project.vamak.core.cfg;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Inherited
public @interface Api {

    @AliasFor(attribute = "version")
    Version value();

    @AliasFor(attribute = "value")
    Version version();

    enum Version {
        V1, V2, V3, V4, V5
    }
}
