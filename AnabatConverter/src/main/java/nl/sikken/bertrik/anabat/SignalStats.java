package nl.sikken.bertrik.anabat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SignalStats {
    
    private double mean;
    private double variance;

    /** List of noise power per chunk, sorted by power */
    private List<Double> noisePower;
    
    /**
     * Calculates signal statistics
     * 
     * @param stream the input stream
     * @param chunkSize size of one chunk for noise calculation
     */
    public void calculate(IAudioStream stream, int chunkSize) throws IOException {
    
        long numFrames = stream.getNumberOfFrames();
        if (chunkSize > numFrames) {
            chunkSize = (int) numFrames;
        }
        double sumX = 0;
        double sumXX = 0;
        int count = 0;
        double localX = 0.0;
        double localXX = 0.0;
        noisePower = new ArrayList<>();
        for (long i = 0; i < numFrames; i++) {
            double sample = stream.getSample();
            
            // local variance and mean
            localX += sample;
            localXX += sample * sample;
            if (++count == chunkSize) {
                count = 0;
                double localMean = localX / chunkSize;
                double localVar = (localXX / chunkSize) - (localMean * localMean);
                noisePower.add(localVar);

                // global variance and mean
                sumX += localX;
                sumXX += localXX;

                localX = 0.0;
                localXX = 0.0;
            }
        }
        
        // sort the list of noise power
        Collections.sort(noisePower);

        this.mean = sumX / numFrames;
        this.variance = (sumXX / numFrames) - (mean * mean);
    }

    public double getMean() {
        return mean;
    }
    
    public double getVariance() {
        return variance;
    }
    
    /**
     * Gets the noise power at <percent> of total number of noise power.
     * 
     * @param percent the percentage
     * @return noise power
     */
    public double getNoisePower(int percent) {
        int size = noisePower.size();
        return noisePower.get(size * percent / 100);
    }
}
