package nl.sikken.bertrik.anabat;

/**
 * @author bertrik
 *
 */
public interface IFilter {
    
    /**
     * Process one sample
     * 
     * @param sample the input sample
     * @return the output sample
     */
    public double process(double sample);
}
