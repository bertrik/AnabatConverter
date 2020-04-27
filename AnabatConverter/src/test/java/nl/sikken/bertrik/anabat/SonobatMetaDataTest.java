package nl.sikken.bertrik.anabat;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public final class SonobatMetaDataTest {

    @Test
    public void test() throws IOException {
        SonobatMetaData metaData = new SonobatMetaData();
        
        File file = new File("8251259-20120211_061851.wav");
        Assert.assertTrue(metaData.load(file));
    }
    
}
