package nl.sikken.bertrik.anabat;

import java.io.IOException;

public interface IAudioStream {

    double getSample() throws IOException;

    int getSampleRate();

    long getNumberOfFrames();

    void close();

}
