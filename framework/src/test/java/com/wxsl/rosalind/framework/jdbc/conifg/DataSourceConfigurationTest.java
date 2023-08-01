package com.wxsl.rosalind.framework.jdbc.conifg;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

class DataSourceConfigurationTest extends BaseTest {

    @Test
    @DisplayName("dataSource")
    void hikariDataSource() throws SQLException {
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        Assertions.assertNotNull(dataSource);
        Assertions.assertNotNull(dataSource.getConnection());
    }
}
