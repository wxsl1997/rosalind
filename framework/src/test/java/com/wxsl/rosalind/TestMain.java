package com.wxsl.rosalind;

import com.wxsl.rosalind.framework.ioc.api.ImportBeanDefinitionRegistrarDemo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(ImportBeanDefinitionRegistrarDemo.class)
@SpringBootApplication
public class TestMain {

}
