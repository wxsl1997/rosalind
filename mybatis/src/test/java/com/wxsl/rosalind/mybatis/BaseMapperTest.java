package com.wxsl.rosalind.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.Reader;

public class BaseMapperTest {

    protected static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void init() throws IOException {
        try (Reader reader = Resources.getResourceAsReader("mybatisConfig.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }
    }

    protected SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}