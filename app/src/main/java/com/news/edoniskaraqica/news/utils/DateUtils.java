package com.news.edoniskaraqica.news.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edonis Karaçica on 3/27/2017.
 */

public class DateUtils {
    public static String formatNewsApiDate(String inputDate){
        try {

            String inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            String outputDateFormat = "EEE, d MMM yyyy KK:mm";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);

            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);

        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return  inputDate;
    }

}
