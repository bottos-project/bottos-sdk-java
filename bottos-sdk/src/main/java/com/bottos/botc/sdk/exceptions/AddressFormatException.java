package com.bottos.botc.sdk.exceptions;

@SuppressWarnings("serial")
public class AddressFormatException extends IllegalArgumentException {
    public AddressFormatException() {
        super();
    }

    public AddressFormatException(String message) {
        super(message);
    }
}
