package com.wxsl.rosalind.mybatis;

import com.wxsl.rosalind.mybatis.mapper.CountryMapper;
import com.wxsl.rosalind.mybatis.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@SpringBootApplication
public class MybatisApplication implements ApplicationRunner {

    private CountryMapper countryMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args)  {
        List<Country> all = countryMapper.findAll();
        System.out.println(all);
    }
}
