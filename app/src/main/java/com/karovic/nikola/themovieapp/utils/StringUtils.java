package com.karovic.nikola.themovieapp.utils;

import android.content.Context;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class StringUtils {

    public static String formatData(String data) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
            return s.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return data;
        }
    }
}
