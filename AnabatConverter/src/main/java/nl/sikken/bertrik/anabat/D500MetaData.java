package nl.sikken.bertrik.anabat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representation of D500 meta data
 * 
 * @author bertrik
 */
public class D500MetaData {

    private String originalFilename;
    private String dateCode;
    private String deviceCode;
    private String settings1;
    private String settings2;
    
    
    /**
     * Loads and parses a wav file and checks if it was generated by a D500
     * 
     * @param file the file to load
     * @return true if the file was a D500 file
     * @throws IOException in case of an IO problem
     */
    public boolean load(File file) throws IOException {
        // read raw data
        byte[] data = new byte[512];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        
        // parse if D500 file
        deviceCode = getString(data, 0xF0, 32);
        if (!deviceCode.startsWith("D500")) {
            return false;
        }

        originalFilename = getString(data, 0xD0, 16); 
        dateCode = getString(data, 0xE0, 16);
        settings1 = getString(data, 0x120, 24);
        settings2 = getString(data, 0x138, 24);
        
        return true;
    }
    
    public static Date convertDateCode(String dateCode) throws ParseException {
        SimpleDateFormat decodeFormat = new SimpleDateFormat("yyMMdd HH:mm:ss");
        return decodeFormat.parse(dateCode);
    }
    
    /**
     * Extracts a string from binary data
     * 
     * @param data the binary data to extract from
     * @param offset offset within the binary data
     * @param maxlen the maximum length of the string
     * @return the extracted string
     * @throws IOException in case of an IO problem
     */
    private String getString(byte[] data, int offset, int maxlen) throws IOException {
        InputStream stream = new ByteArrayInputStream(data);
        if (stream.skip(offset) != offset) {
        	throw new IOException("stream.skip() failed");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxlen; i++) {
            int c = stream.read();
            if ((c == 0) || (c == -1)) {
                break;
            } else {
                sb.append((char) c);
            }
        }
        return sb.toString();
    }
    
    public String getFilename() {
        return originalFilename;
    }
    
    public String getDateCode() {
        return dateCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getSettings1() {
        return settings1;
    }

    public String getSettings2() {
        return settings2;
    }
}
