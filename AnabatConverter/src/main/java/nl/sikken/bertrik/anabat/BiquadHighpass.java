package nl.sikken.bertrik.anabat;

/**
 * Bi-quad high-pass filter
 * 
 * @author bertrik
 */
public class BiquadHighpass extends BiquadFilter {

    /**
     * Constructor
     * 
     * @param fs sample frequency
     * @param f0 high-pass frequency
     * @param q quality factor
     */
    public BiquadHighpass(int fs, double f0, double q) {
        double w0 = 2.0 * Math.PI * f0 / fs;
        double alpha = Math.sin(w0) / (2.0 * q);
        
        double[] b = new double[3];
        double[] a = new double[3];
        
        double cos = Math.cos(w0);
        
        b[0] =  (1.0 + cos) / 2;
        b[1] = -(1.0 + cos);
        b[2] =  (1.0 + cos) / 2;
        a[0] = 1.0 + alpha;
        a[1] = -2.0 * cos;
        a[2] = 1.0 - alpha;
        
        setCoefficients(a, b);
    }

    /**
     * Constructor with default quality parameter
     * 
     * @param fs
     * @param f0
     */
    public BiquadHighpass(int fs, double f0) {
        this(fs, f0, 0.7);
    }

}
