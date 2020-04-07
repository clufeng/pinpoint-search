
package com.hbase.dao.mapper;

/**
 * @author clufeng
 */
public interface ValueMapper<T> {
    byte[] mapValue(T value);
}
