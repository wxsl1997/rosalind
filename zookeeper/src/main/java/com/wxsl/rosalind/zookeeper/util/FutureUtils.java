package com.wxsl.rosalind.zookeeper.util;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.ErrorHandler;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

/**
 * @author wxsl1997
 */
@UtilityClass
public class FutureUtils {

    /**
     * 获取数据, 如果有异常处理器, 则利用异常处理器处理异常, 否则重新抛出异常
     *
     * @param future       feature 对象
     * @param errorHandler 异常处理器
     * @param <T>          数据类型
     * @return 数据
     */
    public <T> T call(Future<T> future, ErrorHandler errorHandler) {

        T result = null;
        try {
            result = future.get();
        } catch (Exception exception) {
            // 如果有异常处理器, 则利用异常处理器处理异常
            if (Objects.nonNull(errorHandler)) {
                errorHandler.handleError(exception);
            }
            // 如果没有异常处理器, 则重新抛出异常
            else {
                throw ExceptionUtils.<RuntimeException>rethrow(exception);
            }
        }
        return result;
    }

    public <T> T call(Future<T> future) {
        // 默认异常
        return call(future, ExceptionUtils::rethrow);
    }


    public <T> List<T> callAll(List<Future<T>> futures) {

        return callAll(futures, ExceptionUtils::rethrow);
    }

    /**
     * 获取数据, 如果有异常处理器且能处理异常, 则利用异常处理器处理异常, 否则重新抛出异常且取消未完成的任务
     *
     * @param futures      任务列表
     * @param errorHandler 异常处理器
     * @param <T>          数据类型
     * @return 数据列表
     */
    public <T> List<T> callAll(List<Future<T>> futures, ErrorHandler errorHandler) {

        List<T> results = Lists.newArrayList();
        try {
            for (Future<T> future : futures) {
                try {
                    T t = future.get();
                    results.add(t);
                } catch (Exception exception) {
                    // 如果有异常处理器, 则利用异常处理器处理异常
                    if (Objects.nonNull(errorHandler)) {
                        errorHandler.handleError(exception);
                    }
                    // 如果没有异常处理器, 则重新抛出异常
                    else {
                        throw ExceptionUtils.<RuntimeException>rethrow(exception);
                    }
                }
            }
        } catch (Exception exception) {
            // 异常处理器未处理异常, 取消未执行完成的任务
            futures.stream().filter(Future::isCancelled).forEach(f -> f.cancel(Boolean.TRUE));
            // 异常重新抛出
            throw ExceptionUtils.<RuntimeException>rethrow(exception);
        }
        return results;
    }

    public static void runAll(List<Future<?>> futures) {
        runAll(futures, ExceptionUtils::rethrow);
    }

    public static void runAll(List<Future<?>> futures, ErrorHandler errorHandler) {
        try {
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (Exception exception) {
                    // 如果有异常处理器, 则利用异常处理器处理异常
                    if (Objects.nonNull(errorHandler)) {
                        errorHandler.handleError(exception);
                    }
                    // 如果没有异常处理器, 则重新抛出异常
                    else {
                        throw ExceptionUtils.<RuntimeException>rethrow(exception);
                    }
                }
            }
        } catch (Exception exception) {
            // 异常处理器未处理异常, 取消未执行完成的任务
            futures.stream().filter(Future::isCancelled).forEach(f -> f.cancel(Boolean.TRUE));
            // 异常重新抛出
            throw ExceptionUtils.<RuntimeException>rethrow(exception);
        }
    }
}
