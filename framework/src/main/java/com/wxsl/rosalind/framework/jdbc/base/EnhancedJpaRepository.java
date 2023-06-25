package com.wxsl.rosalind.framework.jdbc.base;

import com.google.common.collect.Maps;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Map;

public class EnhancedJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private final EntityManager entityManager;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Map<Class<?>, RowMapper<?>> ROW_MAPPER_MAP = Maps.newConcurrentMap();

    public EnhancedJpaRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate(dataSource(entityManager)));
    }

    @Override
    public EntityManager entityManager() {
        return entityManager;
    }

    @Override
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S> RowMapper<S> newBeanPropertyRowMapper(Class<S> clazz) {
        return (RowMapper<S>) ROW_MAPPER_MAP.computeIfAbsent(clazz, key -> new BeanPropertyRowMapper<>(clazz));
    }

    private JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setExceptionTranslator((task, sql, ex) -> null);
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    private DataSource dataSource(EntityManager entityManager) {
        SessionFactoryImpl sf = entityManager.getEntityManagerFactory().unwrap(SessionFactoryImpl.class);
        return ((DatasourceConnectionProviderImpl) sf.getServiceRegistry().getService(ConnectionProvider.class)).getDataSource();
    }
}
