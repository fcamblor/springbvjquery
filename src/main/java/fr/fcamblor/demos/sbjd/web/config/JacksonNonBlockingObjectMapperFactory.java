package fr.fcamblor.demos.sbjd.web.config;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.deser.std.StdKeyDeserializer;
import org.codehaus.jackson.map.module.SimpleModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fcamblor
 * Jackson ObjectMapper factory that will allow to decorate deserializers/serializers with non blocking
 * decorators (conversion type won't block/throw NPE when serializing/deserializing values)
 */
public class JacksonNonBlockingObjectMapperFactory {
    /**
     * Deserializer that won't block if value parsing doesn't match with target type
     * @param <T> Handled type
     */
    private static class NonBlockingDeserializer<T> extends JsonDeserializer<T> {
        private StdDeserializer<T> delegate;

        public NonBlockingDeserializer(StdDeserializer<T> _delegate){
            this.delegate = _delegate;
        }

        @Override
        public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            try {
                return delegate.deserialize(jp, ctxt);
            }catch (JsonMappingException e){
                // If a JSON Mapping occurs, simply returning null instead of blocking things
                return null;
            }
        }
    }

    private static class NonBlockingKeyDeserializer extends KeyDeserializer {
        private StdKeyDeserializer delegate;

        public NonBlockingKeyDeserializer(StdKeyDeserializer delegate){
            this.delegate = delegate;
        }

        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            try {
                return delegate.deserializeKey(key, ctxt);
            }catch (JsonMappingException e){
                // If a JSON Mapping occurs, simply returning null instead of blocking things
                return null;
            }
        }
    }

    private List<JsonSerializer> jsonSerializers = new ArrayList<JsonSerializer>();
    private List<StdDeserializer> jsonDeserializers = new ArrayList<StdDeserializer>();
    private List<StdKeyDeserializer> jsonKeyDeserializers = new ArrayList<StdKeyDeserializer>();

    public ObjectMapper createObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule projectJacksonModule = new SimpleModule("projectJacksonModule", new Version(1, 0, 0, null));
        for(JsonSerializer jsonSerializer : jsonSerializers){
            projectJacksonModule.addSerializer(jsonSerializer.handledType(), jsonSerializer);
        }
        for(StdDeserializer jsonDeserializer : jsonDeserializers){
            // Wrapping given deserializers with NonBlockingDeserializer
            projectJacksonModule.addDeserializer(jsonDeserializer.getValueClass(), new NonBlockingDeserializer(jsonDeserializer));
        }
        for(StdKeyDeserializer jsonKeyDeserializer : jsonKeyDeserializers){
            // Wrapping given deserializers with NonBlockingDeserializer
            projectJacksonModule.addKeyDeserializer(jsonKeyDeserializer.getKeyClass(), new NonBlockingKeyDeserializer(jsonKeyDeserializer));
        }

        objectMapper.registerModule(projectJacksonModule);
        return objectMapper;
    }

    public JacksonNonBlockingObjectMapperFactory setJsonDeserializers(List<StdDeserializer> _jsonDeserializers){
        this.jsonDeserializers = _jsonDeserializers;
        return this;
    }

    public JacksonNonBlockingObjectMapperFactory setJsonSerializers(List<JsonSerializer> _jsonSerializers){
        this.jsonSerializers = _jsonSerializers;
        return this;
    }

    public JacksonNonBlockingObjectMapperFactory setJsonKeyDeserializers(List<StdKeyDeserializer> _jsonKeyDeserializers){
        this.jsonKeyDeserializers = _jsonKeyDeserializers;
        return this;
    }
}
