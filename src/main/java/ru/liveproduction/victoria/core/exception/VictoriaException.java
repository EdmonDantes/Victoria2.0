/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.exception;

public class VictoriaException extends RuntimeException {
    public VictoriaException() {
        super();
    }

    public VictoriaException(String message) {
        super(message);
    }

    public VictoriaException(String message, Object... args) {
        super(String.format(message, args));
    }

    public VictoriaException(String message, Throwable cause) {
        super(message, cause);
    }

    public VictoriaException(Throwable cause) {
        super(cause);
    }

    protected VictoriaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
