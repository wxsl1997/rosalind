package com.wxsl.rosalind.mybatis.mapper;

import com.wxsl.rosalind.mybatis.model.Country;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface CountryMapper {

    @SelectProvider(type = CountrySql.class, method = "findAll")
    List<Country> findAll();

    @Select("select * from country where id = #{id}")
    Country findById(Long id);

    void save(Country country);

    void update(Country country);

    void deleteById(Long id);

    @UtilityClass
    class CountrySql {

        public static String findAll() {
            return "select * from country";
        }
    }
}
