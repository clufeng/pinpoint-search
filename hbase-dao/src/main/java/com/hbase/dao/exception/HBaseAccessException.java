
package com.hbase.dao.exception;

/**
 *
 */
public class HBaseAccessException extends RuntimeException {

    public HBaseAccessException() {
    }

    public HBaseAccessException(String message) {
        super(message);
    }

    public HBaseAccessException(Throwable cause) {
        super(cause);
    }

    public HBaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
