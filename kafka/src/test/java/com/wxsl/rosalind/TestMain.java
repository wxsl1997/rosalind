package com.wxsl.rosalind;

import com.wxsl.rosalind.kafka.KafkaApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@ComponentScan(basePackageClasses = TestMain.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                KafkaApplication.class,
        })
})
@SpringBootApplication
public class TestMain {

}
