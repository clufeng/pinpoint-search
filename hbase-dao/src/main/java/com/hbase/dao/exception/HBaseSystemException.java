
package com.hbase.dao.exception;


/**
 * HBase Data Access exception.
 */
@SuppressWarnings("serial")
public class HBaseSystemException extends HBaseAccessException {

    public HBaseSystemException(Exception cause) {
        super(cause.getMessage(), cause);
    }
}
