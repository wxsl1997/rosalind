package com.wxsl.rosalind.framework.jdbc.dao;

import com.google.common.collect.Iterables;
import com.wxsl.rosalind.framework.jdbc.base.BaseJpaRepository;
import com.wxsl.rosalind.framework.jdbc.model.TradeRate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TradeRateDao extends BaseJpaRepository<TradeRate, Long> {

    default List<TradeRate> listTradeRate() {
        String sql = "select * from trade_rate";
        return jdbcTemplate().query(sql, newBeanPropertyRowMapper(TradeRate.class));
    }

    default TradeRate findByTid(Long tid) {
        String sql = "select * from trade_rate where tid = :tid";

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("tid", tid);

        List<TradeRate> result = jdbcTemplate().query(sql, paramSource, newBeanPropertyRowMapper(TradeRate.class));
        return Iterables.getFirst(result, null);
    }
}
