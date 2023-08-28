package com.wxsl.rosalind.spring.ioc.api;

import java.util.Collection;

public interface TradeRepository {

    Integer findByUserIdAndTidIn(Long userId, Collection<Long> tids);

    Integer findByTidIn(Integer serverId, Collection<Long> tids);
}
