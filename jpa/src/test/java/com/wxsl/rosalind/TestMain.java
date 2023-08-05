package com.wxsl.rosalind;

import com.wxsl.rosalind.jpa.JpaApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@ComponentScan(basePackageClasses = TestMain.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                JpaApplication.class,
        })
})
@SpringBootApplication
public class TestMain {

}
