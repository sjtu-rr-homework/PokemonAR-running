package org.pokemonrun.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtils {
    public static String format(Timestamp time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }
    public static Timestamp parse(String time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return new Timestamp(sdf.parse(time).getTime());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static String format(Timestamp time){
        return format(time, "yyyy-MM-dd HH:mm:ss");
    }
}
