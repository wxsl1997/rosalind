package com.wxsl.rosalind.kafka.serializer;

import com.wxsl.rosalind.kafka.util.JsonUtils;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * @author wxsl1997
 */
public class WxslJsonSerializer<T> extends JsonSerializer<T> {

    public WxslJsonSerializer() {
        super(JsonUtils.objectMapper());
    }
}
