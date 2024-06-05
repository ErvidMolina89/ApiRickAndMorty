package com.wposs.apirickandmorty.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatDateString(String inputDate, DateFormatType outputFormat) {
        String inputFormat = "yyyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; // The format of the input date string
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat.getFormat(), Locale.getDefault());
        String outputDate = "";

        try {
            Date date = inputDateFormat.parse(inputDate);
            outputDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }

}
