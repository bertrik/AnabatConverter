package nl.sikken.bertrik.anabat;

public interface IFilter {
    
    /**
     * Process one sample
     * 
     * @param sample the input sample
     * @return the output sample
     */
    double process(double sample);
}
