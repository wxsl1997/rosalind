
package com.wxsl.rosalind.zk.leader;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
public class ChildrenCache {

    @Getter
    private List<String> cache;

    /**
     * 找出新增的数据并刷新缓存
     *
     * @param children 最新缓存数据
     * @return 新增的数据
     */
    List<String> getNewAndRefreshCache(List<String> children) {

        Set<String> exists = Optional.ofNullable(this.cache).map(Sets::newHashSet).orElse(new HashSet<>());
        List<String> absentWorkers = children.stream().filter(c -> !exists.contains(c)).collect(Collectors.toList());

        this.cache = children;

        return absentWorkers;
    }


    /**
     * 获取已经失效的缓存集合并更新缓存
     *
     * @param children 最新缓存数据
     * @return 已经失效的缓存集合
     */
    List<String> getAbsentCacheAndRefreshCache(List<String> children) {

        List<String> exists = Optional.ofNullable(this.cache).orElse(Lists.newArrayList());
        List<String> absentWorkers = exists.stream().filter(c -> !children.contains(c)).collect(Collectors.toList());

        this.cache = children;

        return absentWorkers;
    }


}
