package com.wxsl.rosalind.spring.ioc.api;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

public class ImportBeanDefinitionRegistrarDemo implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(BeanDefinitionDemoFactoryBean.class.getName());

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        beanDefinition.setAttribute(FactoryBean.OBJECT_TYPE_ATTRIBUTE, BeanDefinitionDemoFactoryBean.class.getName());

        registry.registerBeanDefinition("beanDefinitionDemoApi", beanDefinition);
    }

    public static class BeanDefinitionDemoFactoryBean implements FactoryBean<TradeRepository> {

        @Override
        public TradeRepository getObject() {

            InvocationHandler invocationHandler = (proxy, method, args) -> {

                Method executable = Target.class.getMethod(method.getName(), method.getParameterTypes());
                return executable.invoke(new Target(), args);
            };

            return (TradeRepository) Proxy.newProxyInstance(TradeRepository.class.getClassLoader(), new Class[]{TradeRepository.class}, invocationHandler);
        }

        @Override
        public Class<?> getObjectType() {
            return TradeRepository.class;
        }
    }

    public static class Target {

        public Integer findByUserIdAndTidIn(Long userId, Collection<Long> tids) {
            return tids.size();
        }

        public Integer findByTidIn(Integer serverId, Collection<Long> tids) {
            return tids.size();
        }
    }
}
