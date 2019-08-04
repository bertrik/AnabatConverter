package nl.sikken.bertrik.anabat;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class SonobatMetaDataTest extends TestCase {

    public void test() throws IOException {
        SonobatMetaData metaData = new SonobatMetaData();
        
        File file = new File("8251259-20120211_061851.wav");
        assertTrue(metaData.load(file));
    }
    
}
