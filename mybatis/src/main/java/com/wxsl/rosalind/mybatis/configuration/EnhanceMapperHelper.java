package com.wxsl.rosalind.mybatis.configuration;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * @author wxsl1997
 */
@UtilityClass
public final class EnhanceMapperHelper {

    /**
     * @see ServiceImpl#saveBatch(Collection)
     */
    public static <T> void saveBatch(EnhancedMapper<T> proxyMapper, Collection<T> entities, int batchSize) {
        Class<?> mapper = deduceMapper(proxyMapper);
        String sqlStatement = SqlHelper.getSqlStatement(mapper, SqlMethod.INSERT_ONE);
        executeBatch(mapper, entities, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    /**
     * @see ServiceImpl#saveOrUpdate(java.lang.Object)
     */
    public static <T> int saveOrUpdate(EnhancedMapper<T> proxyMapper, T entity) {
        Class<?> entityClass = entityClass(proxyMapper);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
        return StringUtils.checkValNull(idVal) ? proxyMapper.insert(entity) : proxyMapper.updateById(entity);
    }

    public static <T> void updateBatchByIds(EnhancedMapper<T> proxyMapper, Collection<T> entities, int batchSize) {
        Class<?> mapper = deduceMapper(proxyMapper);
        String sqlStatement = SqlHelper.getSqlStatement(mapper, SqlMethod.UPDATE_BY_ID);
        executeBatch(mapper, entities, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    private static <T> void executeBatch(Class<?> mapper, Collection<T> entities, int batchSize, BiConsumer<SqlSession, T> consumer) {
        Class<?> entityClass = GenericTypeResolver.resolveTypeArgument(mapper, EnhancedMapper.class);
        Log log = LogFactory.getLog(mapper);
        SqlHelper.executeBatch(entityClass, log, entities, batchSize, consumer);
    }

    private static <T> Class<?> deduceMapper(EnhancedMapper<T> proxyMapper) {
        //noinspection OptionalGetWithoutIsPresent
        return Arrays.stream(proxyMapper.getClass().getInterfaces()).filter(EnhancedMapper.class::isAssignableFrom).findFirst().get();
    }

    public static <T> Class<T> entityClass(EnhancedMapper<T> proxyMapper) {
        Class<?> mapper = deduceMapper(proxyMapper);
        //noinspection unchecked
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(mapper, EnhancedMapper.class);
    }
}
