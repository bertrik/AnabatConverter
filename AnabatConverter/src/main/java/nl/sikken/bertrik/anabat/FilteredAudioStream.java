package nl.sikken.bertrik.anabat;

import java.io.IOException;

/**
 * Filtered audio stream
 * 
 * @author bertrik
 */
public class FilteredAudioStream implements IAudioStream {

    private final IAudioStream stream;
    private final IFilter filter;

    /**
     * @param stream the stream to be filtered
     * @param filter the filter to apply
     */
    public FilteredAudioStream(IAudioStream stream, IFilter filter) {
        this.stream = stream;
        this.filter = filter;
    }
    
    /* (non-Javadoc)
     * @see nl.sikken.bertrik.anabat.IAudioStream#close()
     */
    public void close() {
        stream.close();
    }

    /* (non-Javadoc)
     * @see nl.sikken.bertrik.anabat.IAudioStream#getNumberOfFrames()
     */
    public long getNumberOfFrames() {
        return stream.getNumberOfFrames();
    }

    /* (non-Javadoc)
     * @see nl.sikken.bertrik.anabat.IAudioStream#getSample()
     */
    public double getSample() throws IOException {
        return filter.process(stream.getSample());
    }

    /* (non-Javadoc)
     * @see nl.sikken.bertrik.anabat.IAudioStream#getSampleRate()
     */
    public int getSampleRate() {
        return stream.getSampleRate();
    }

}
