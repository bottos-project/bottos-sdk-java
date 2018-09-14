package com.bottos.botc.sdk.exceptions;

/**
 * Created by xionglh on 2018/9/6
 */
public class BotcException   extends RuntimeException {

    private Integer errorCode;
    private String errorDesc;

    public BotcException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = BotcError.SYSTEM_ERROR.getCode();
        this.errorDesc = message;
    }

    public BotcException(Throwable cause) {
        super(cause);
        this.errorCode = BotcError.SYSTEM_ERROR.getCode();
        this.errorDesc = BotcError.SYSTEM_ERROR.getDescription();
    }

    public BotcException(BotcError errEnum) {
        this(errEnum.getCode(), errEnum.getDescription());
    }

    public BotcException(BotcError errEnum, String message) {
        this(errEnum.getCode(), message);
    }

    public BotcException(Integer errCode, String message) {
        super(message);
        this.errorCode = errCode;
        this.errorDesc = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

}
