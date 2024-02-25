package nl.sikken.bertrik.anabat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZeroCrossFinder {
    
    public List<Long> processStream(IAudioStream stream, double offset, double threshold, int expRatio, int divRatio) throws IOException {
        List<Long> list = new ArrayList<Long>();
        int divCount = 0;
        double prevSample = 0;
        long position = 0;
        
        int sampleRate = stream.getSampleRate() * expRatio;
        long numFrames = stream.getNumberOfFrames();
        
        for (int i = 0; i < numFrames; i++) {
            double sample = stream.getSample() - offset;
            if (((threshold < 0) && (sample < threshold)) ||
                ((threshold > 0) && (sample > threshold))) {
                // zero crossing
                if (++divCount == divRatio) {
                    double delta = (sample - threshold) / (sample - prevSample);
                    double time = (position - delta) / sampleRate;
                    long usec = (long) (1E6 * time);
                    list.add(usec);
                    divCount = 0;
                }
                threshold = -threshold;
            }
            prevSample = sample;
            position++;
        }
        
        return list;
    }
    
}
