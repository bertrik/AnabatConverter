package nl.sikken.bertrik.anabat;

import java.io.IOException;

public interface IAudioStream {

    /**
     * @return a 
     * @throws IOException 
     */
    public double getSample() throws IOException;

    public int getSampleRate();

    public long getNumberOfFrames();

    public void close();

}
