package com.bottos.botc.sdk.exceptions;

/**
 * Created by xionglh on 2018/9/6
 */
public enum BotcError {

    ACCOUNT_PWD_ERROR(200, "password not empty"),
    ACCOUNT_NAME_ERROR(201, "name not empty"),
    PUBLICKYE_EMPTY_ERR(202,"publickey not empty"),
    PRIVATEKEY_EMPTY_ERR(203,"privatekey not empty"),
    PARAMS_EMPTY_EMPTY_ERR(204,"params not empty"),
    SYSTEM_ERROR(100, "System error");

    private final Integer code;
    private final String description;

    BotcError(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
