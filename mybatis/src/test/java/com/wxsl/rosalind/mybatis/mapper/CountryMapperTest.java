package com.wxsl.rosalind.mybatis.mapper;

import com.wxsl.rosalind.mybatis.BaseMapperTest;
import com.wxsl.rosalind.mybatis.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
class CountryMapperTest extends BaseMapperTest {

    @Test
    public void testFindAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            List<Country> countries = sqlSession.selectList("com.wxsl.rosalind.mybatis.mapper.CountryMapper.findAll");
            log.info("test find all, countries:{}", countries);
        }
    }

    @Test
    public void testFindById() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            countryMapper.findAll().stream().findAny().ifPresent(item -> {
                Country country = countryMapper.findById(item.getId());
                log.info("test find by id, country:{}", country);
            });
        }
    }

    @Test
    public void testSave() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            Country country = new Country();
            country.setName("英国");
            country.setCode("GB");
            countryMapper.save(country);
            Assertions.assertNotNull(country.getId());
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            countryMapper.findAll().stream().findAny().ifPresent(country -> {
                String name = "china";
                country.setName(name);
                countryMapper.update(country);
                Assertions.assertEquals(name, countryMapper.findById(country.getId()).getName());
            });
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            countryMapper.findAll().stream().findAny().ifPresent(country -> {
                countryMapper.deleteById(country.getId());
                Assertions.assertNull(countryMapper.findById(country.getId()));
            });
        }
    }
}