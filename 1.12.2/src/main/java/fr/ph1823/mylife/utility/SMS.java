package fr.ph1823.mylife.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SMS {
    private String message;
    private Date date;
    private final SimpleDateFormat SMS_FORMATTER = new SimpleDateFormat("dd MM yyyy HH:mm:ss");


    public SMS(String message, String dateStr) throws ParseException {
        this.message = message;
        this.date = SMS_FORMATTER.parse(dateStr);
    }

    public SMS(String message, Date date) {
        this.message = message;
        this.date = date;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return SMS_FORMATTER.format(date) + "::" + this.message;
    }
}
