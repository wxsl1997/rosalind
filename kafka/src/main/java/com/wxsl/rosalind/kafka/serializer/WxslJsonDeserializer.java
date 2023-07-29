package com.wxsl.rosalind.kafka.serializer;

import com.wxsl.rosalind.kafka.util.JsonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * @author wxsl1997
 */
public class WxslJsonDeserializer<T> extends JsonDeserializer<T> {

    public WxslJsonDeserializer() {
        super(JsonUtils.objectMapper());
    }
}
