package com.wxsl.rosalind.jpa.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class IntEnumType<E extends Enum<E> & IntEnum> implements UserType, DynamicParameterizedType {
    /**
     * 枚举类
     */
    private Class<E> clazz;
    public static final String IntEnum = "com.wxsl.rosalind.jpa.util.IntEnumType";

    @Override
    public void setParameterValues(Properties parameters) {
        final ParameterType reader = (ParameterType) parameters.get(PARAMETER_TYPE);

        if (reader != null) {
            //noinspection unchecked
            clazz = reader.getReturnedClass().asSubclass(Enum.class);
        } else {
            String message = String.format("enum class not found for entity:%s, field:%s", parameters.get(ENTITY), parameters.get(PROPERTY));
            throw new HibernateException(message);
        }
    }


    @Override
    public int[] sqlTypes() {
        return new int[]{Types.INTEGER};
    }

    @Override
    public Class<E> returnedClass() {
        return clazz;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        final int value = rs.getInt(names[0]);
        return rs.wasNull() ? null : codeOf(value);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        st.setObject(index, ((IntEnum) value).value(), Types.INTEGER);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    private E codeOf(int code) {
        try {
            return EnumUtils.valueToEnum(clazz, code);
        } catch (Exception ex) {
            String message = String.format("cannot convert int to enum, enum:%s, value:%s", clazz.getSimpleName(), code);
            throw new IllegalArgumentException(message, ex);
        }
    }
}