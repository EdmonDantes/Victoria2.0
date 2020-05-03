/*******************************************************************************
 *
 * Copyright © (c). 2020. Loginov Ilya Vladislavovich. All Rights Reserved.
 *
 *******************************************************************************/

package ru.liveproduction.victoria.core.logging;

public interface ILoggerConfigurator {

    static String getLoggerName(Class<?> _class) {
        return _class + "@static";
    }

    static String getLoggerName(Object obj) {
        return obj.getClass() + "@" + obj.hashCode();
    }

}
