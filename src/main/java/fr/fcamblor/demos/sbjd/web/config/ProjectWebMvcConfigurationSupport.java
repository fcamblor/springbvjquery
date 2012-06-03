package fr.fcamblor.demos.sbjd.web.config;

import fr.fcamblor.demos.sbjd.web.exceptions.GlobalExceptionHandlerMethodExceptionResolver;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.deser.std.CalendarDeserializer;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.deser.std.StdKeyDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author fcamblor
 */
@Configuration
public class ProjectWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.configureHandlerExceptionResolvers(exceptionResolvers);

        // Adding GlobalExceptionHandlerMethodExceptionResolver that will allow to provide
        // "global" (not @Controller specific) @ExceptionHandler
        GlobalExceptionHandlerMethodExceptionResolver bindingResultMethodExceptionResolver = new GlobalExceptionHandlerMethodExceptionResolver();
        bindingResultMethodExceptionResolver.setMessageConverters(getMessageConverters());
        bindingResultMethodExceptionResolver.afterPropertiesSet();
        exceptionResolvers.add(bindingResultMethodExceptionResolver);
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Overriding default MappingJacksonHttpMessageConverter's ObjectMapper in order to have an ObjectMapper
        // which doesn't block (throw error) on bad type conversion (if you submit "toto" as a Float without this overriding,
        // you would encounter a NPE)

        // Looking for MappingJacksonHttpMessageConverter
        for(HttpMessageConverter<?> converter : converters){
            if(converter instanceof MappingJacksonHttpMessageConverter){
                // Overriding object mapper with custom one
                ((MappingJacksonHttpMessageConverter)converter).setObjectMapper(configureObjectMapperFactory().createObjectMapper());
            }
        }
    }

    protected JacksonNonBlockingObjectMapperFactory configureObjectMapperFactory() {
        JacksonNonBlockingObjectMapperFactory factory = new JacksonNonBlockingObjectMapperFactory();

        // If necessary, add your custom serializers here
        factory.setJsonSerializers(Collections.<JsonSerializer>emptyList());

        // Configuring default jackson deserializers
        factory.setJsonDeserializers(Arrays.<StdDeserializer>asList(
                new StdDeserializer.ShortDeserializer(Short.class, null),
                new StdDeserializer.IntegerDeserializer(Integer.class, null),
                new StdDeserializer.CharacterDeserializer(Character.class, null),
                new StdDeserializer.LongDeserializer(Long.class, null),
                new StdDeserializer.FloatDeserializer(Float.class, null),
                new StdDeserializer.DoubleDeserializer(Double.class, null),
                new StdDeserializer.NumberDeserializer(),
                new StdDeserializer.BigDecimalDeserializer(),
                new StdDeserializer.BigIntegerDeserializer(),
                new CalendarDeserializer()
        ));

        // If necessary, add your custom key deserializers here
        factory.setJsonKeyDeserializers(Collections.<StdKeyDeserializer>emptyList());

        return factory;
    }
}
