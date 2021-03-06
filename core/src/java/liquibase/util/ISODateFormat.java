package liquibase.util;

import liquibase.database.sql.ComputedDateValue;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ISODateFormat {

    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT_STRING);
    private SimpleDateFormat dateTimeFormatWithDecimal = new SimpleDateFormat(DATE_TIME_FORMAT_STRING_WITH_DECIMAL);
    private SimpleDateFormat dateTimeFormatWithSpace = new SimpleDateFormat(DATE_TIME_FORMAT_STRING_WITH_SPACE);
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATE_TIME_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DATE_TIME_FORMAT_STRING_WITH_SPACE = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_FORMAT_STRING_WITH_DECIMAL = "yyyy-MM-dd'T'HH:mm:ss.S";


    public String format(java.sql.Date date) {
        return dateFormat.format(date);
    }

    public String format(java.sql.Time date) {
        return timeFormat.format(date);
    }

    public String format(java.sql.Timestamp date) {
        return dateTimeFormatWithDecimal.format(date);
    }

    public String format(Date date) {
        if (date instanceof ComputedDateValue) {
            return date.toString();
        } else if (date instanceof java.sql.Date) {
            return format(((java.sql.Date) date));
        } else if (date instanceof Time) {
            return format(((java.sql.Time) date));
        } else if (date instanceof java.sql.Timestamp) {
            return format(((java.sql.Timestamp) date));
        } else {
            throw new RuntimeException("Unknown type: "+date.getClass().getName());
        }
    }

    public Date parse(String dateAsString) throws ParseException {
        SimpleDateFormat dateTimeFormat = this.dateTimeFormat;

        if (dateAsString.indexOf('.') >= 0) {
            dateTimeFormat = this.dateTimeFormatWithDecimal;
        } else if (dateAsString.indexOf(' ') >= 0) {
            dateTimeFormat = this.dateTimeFormatWithSpace;
        }

        if (dateAsString.length() != dateFormat.toPattern().length() && dateAsString.length() != timeFormat.toPattern().length()) { //subtract 2 to not count the 's
            return new java.sql.Timestamp(dateTimeFormat.parse(dateAsString).getTime());
        } else {
            if (dateAsString.indexOf(':') > 0) {
                return new java.sql.Time(timeFormat.parse(dateAsString).getTime());
            } else {
                return new java.sql.Date(dateFormat.parse(dateAsString).getTime());
            }
        }
    }
}
