package by.s0mmelier.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class UtilService {

    public Date stringToDate(String dataInString) throws ParseException {
        if (dataInString.equals("undefined") || dataInString == null) return  null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        return formatter.parse(dataInString);
    }


}
