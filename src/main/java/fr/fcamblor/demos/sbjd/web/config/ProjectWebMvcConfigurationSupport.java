package fr.fcamblor.demos.sbjd.web.config;

import fr.fcamblor.demos.sbjd.web.exceptions.GlobalExceptionHandlerMethodExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author fcamblor
 */
@Configuration
public class ProjectWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.configureHandlerExceptionResolvers(exceptionResolvers);

        GlobalExceptionHandlerMethodExceptionResolver bindingResultMethodExceptionResolver = new GlobalExceptionHandlerMethodExceptionResolver();
        bindingResultMethodExceptionResolver.setMessageConverters(getMessageConverters());
        bindingResultMethodExceptionResolver.afterPropertiesSet();
        exceptionResolvers.add(bindingResultMethodExceptionResolver);
    }
}
