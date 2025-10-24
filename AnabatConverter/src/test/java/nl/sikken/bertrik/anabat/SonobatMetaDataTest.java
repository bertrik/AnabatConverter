package nl.sikken.bertrik.anabat;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class SonobatMetaDataTest {

    @Test
    public void test() throws IOException {
        SonobatMetaData metaData = new SonobatMetaData();
        
        File file = new File("8251259-20120211_061851.wav");
        assertTrue(metaData.load(file));
    }
    
}
