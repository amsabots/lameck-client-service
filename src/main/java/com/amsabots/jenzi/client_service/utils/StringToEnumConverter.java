package com.amsabots.jenzi.client_service.utils;



import org.springframework.core.convert.converter.Converter;

import java.util.Locale;

/**
 * @author andrew mititi on Date 11/10/21
 * @Project lameck-client-service
 */

/*
 *  ======================================================================================================
 *     Converts the incoming string on the http request to a valid enum type as defined by the enum object
 * =======================================================================================================
 * */
public class StringToEnumConverter implements Converter<String, TaskState> {

    @Override
    public TaskState convert(String source) {
        return TaskState.valueOf(source.toUpperCase());
    }
}

