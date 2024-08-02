package ir.snapp.pay.side.project.vamak.core.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.web.method.HandlerTypePredicate.forAnnotation;

@Configuration
public class VamakWebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api", forAnnotation(Api.class));
    }
}
