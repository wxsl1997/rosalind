package com.wxsl.rosalind.spring.ioc.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.FactoryBean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@AllArgsConstructor
public class FactoryBeanDemo implements FactoryBean<MessageDigest> {

    private final String algorithm;

    @Override
    public MessageDigest getObject() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }
}
