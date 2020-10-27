
package com.apang.icecream.auth.exception;
/**
 * 异常类.<br>
 * @author andy pang <br>
 * @version 1.0.0 2019年8月29日<br>
 * @since JDK 1.8.0
 */
public class OffLineException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
      * 创建一个新的实例 
      */
    public OffLineException() {
        super();
    }

    /**
      * 创建一个新的实例 
      * @param message
      * @param cause
      */
    public OffLineException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
      * 创建一个新的实例 
      * @param message
      */
    public OffLineException(String message) {
        super(message);
    }

    /**
      * 创建一个新的实例 
      * @param cause
      */
    public OffLineException(Throwable cause) {
        super(cause);
    }

}
