package nl.sikken.bertrik.anabat;

/**
 * Generic bi-quad filter
 * 
 * Extends this class for a specific filter implementation (high-pass, low-pass, band-pass etc.)
 * 
 * @author bertrik
 */
public abstract class BiquadFilter implements IFilter {

    private double[] x = new double[3];
    private double[] y = new double[3];
    
    private double[] a;
    private double[] b;
    
    /* (non-Javadoc)
     * @see nl.sikken.bertrik.anabat.IFilter#process(double)
     */
    public double process(double in) {
        x[0] = in;
        y[0] = (b[0] * x[0] + b[1] * x[1] + b[2] * x[2] - a[1] * y[1] - a[2] * y[2]);
        
        // move x in time
        x[2] = x[1];
        x[1] = x[0];
        
        // move y in time
        y[2] = y[1];
        y[1] = y[0];
        
        return y[0];
    }

    /**
     * Configures the filter coefficients
     * 
     * @param a the feedback weights
     * @param b the forward weights
     */
    protected void setCoefficients(double[] a, double[] b) {
        // copy and pre-scale coefficients
        this.a = new double[a.length];
        this.b = new double[b.length];
        double a0 = a[0];
        this.b[0] = b[0] / a0;
        for (int i = 1; i < a.length; i++) {
            this.a[i] = a[i] / a0;
            this.b[i] = b[i] / a0;
        }
        
        // clear filter state
        for (int i = 0; i < x.length; i++) {
            x[i] = 0.0;
            y[i] = 0.0;
        }
    }
}
