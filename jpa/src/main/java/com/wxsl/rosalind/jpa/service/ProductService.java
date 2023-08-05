package com.wxsl.rosalind.jpa.service;

import com.google.common.collect.Lists;
import com.wxsl.rosalind.jpa.command.ProductAddCommand;
import com.wxsl.rosalind.jpa.dto.ProductDto;
import com.wxsl.rosalind.jpa.mapping.ProductMapper;
import com.wxsl.rosalind.jpa.model.Product;
import com.wxsl.rosalind.jpa.query.ProductCriteriaQuery;
import com.wxsl.rosalind.jpa.query.ProductExampleQuery;
import com.wxsl.rosalind.jpa.repository.ProductRepository;
import com.wxsl.rosalind.jpa.util.StreamUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author wxsl1997
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class ProductService {

    ProductRepository productRepository;

    ProductMapper productMapper;

    public void saveProduct(List<ProductAddCommand> dtos) {
        List<Product> products = StreamUtils.map(dtos, productMapper::toProduct);
        productRepository.saveAll(products);
    }

    public List<ProductDto> findByExample(ProductExampleQuery query) {
        // not recommended in general
        Product product = productMapper.toProduct(query);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact());

        List<Product> rows = productRepository.findAll(Example.of(product, matcher));
        return StreamUtils.map(rows, productMapper::toProductDto);
    }

    public List<ProductDto> findByCriteria(ProductCriteriaQuery query) {

        Specification<Product> specification = (root, criteria, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (Objects.nonNull(query.getId())) {
                predicates.add(cb.equal(root.get("id"), query.getId()));
            }
            if (StringUtils.isNotEmpty(query.getName())) {
                predicates.add(cb.equal(root.get("name"), query.getName()));
            }
            if (Objects.nonNull(query.getStock())) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("stock"), query.getStock()));
            }
            if (Objects.nonNull(query.getPrice())) {
                predicates.add(cb.between(root.get("price"), BigDecimal.ZERO, query.getPrice()));
            }
            return criteria.where(predicates.toArray(new Predicate[0])).getRestriction();
        };

        List<Product> rows = productRepository.findAll(specification);
        return StreamUtils.map(rows, productMapper::toProductDto);
    }
}
