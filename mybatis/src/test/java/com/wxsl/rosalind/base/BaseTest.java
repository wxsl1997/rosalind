package com.wxsl.rosalind.base;

import com.wxsl.rosalind.TestMain;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ContextConfiguration(classes = {TestMain.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(value = {"classpath:config/test.properties"})
public abstract class BaseTest {

    @Resource
    protected ApplicationContext applicationContext;

    public SqlSession getSqlSession() {
        return applicationContext.getBean(SqlSessionFactory.class).openSession();
    }
}
