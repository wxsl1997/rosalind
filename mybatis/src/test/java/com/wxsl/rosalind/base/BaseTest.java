package com.wxsl.rosalind.base;

import com.wxsl.rosalind.TestMain;
import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TestMain.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(value = {"classpath:config/test.properties"})
@MybatisTransactional
public abstract class BaseTest {


}
