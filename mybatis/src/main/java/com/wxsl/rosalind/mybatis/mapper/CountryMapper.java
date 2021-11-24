package com.wxsl.rosalind.mybatis.mapper;

import com.wxsl.rosalind.mybatis.model.Country;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CountryMapper {

    List<Country> findAll();

    @Select("select * from country where id = #{id}")
    Country findById(Long id);

    void save(Country country);

    void update(Country country);

    void deleteById(Long id);
}
