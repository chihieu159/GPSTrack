package com.example.demo.domain.utils;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDateTime convertStringToDate(String value, String pattern){

        value = value.replace( "Z" , "+0000" );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime convertedDate = LocalDateTime.parse(value, formatter);

        return convertedDate;
    }
}
