package nl.sikken.bertrik.anabat;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SonobatMetaData {

    private static final Pattern PATTERN = Pattern.compile("(\\d{7})-(\\d{8}_\\d{6}).wav");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    
    private Date date;
    private String code;
    
    /**
     * @param file
     * @return
     * @throws IOException
     */
    public boolean load(File file) throws IOException {
        String name = file.getName().toLowerCase();
        Matcher matcher = PATTERN.matcher(name);
        if (!matcher.matches()) {
            return false;
        }

        String dateCode = matcher.group(2);
        try {
            this.date = DATE_FORMAT.parse(dateCode);
        } catch (ParseException e) {
            return false;
        }
        
        this.code = matcher.group(1);
        return true;
    }
    
    /**
     * @return
     */
    public Date getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }
    
}
