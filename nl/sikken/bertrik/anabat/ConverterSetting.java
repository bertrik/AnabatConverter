package nl.sikken.bertrik.anabat;

/**
 * Settings for the converter
 * 
 * @author bertrik
 */
public class ConverterSetting {

    private final int channel;
    private final String note;
    private final int divRatio;
    private final int expRatio;
    private final int highPass;

    public ConverterSetting(int channel, String note, int divRatio, int expRatio, int highPass) {
        this.channel = channel;
        this.note = note;
        this.divRatio = divRatio;
        this.expRatio = expRatio;
        this.highPass = highPass;
    }
    
    public int getDivRatio() {
        return divRatio;
    }

    public String getNote() {
        return note;
    }

    public int getExpRatio() {
        return expRatio;
    }
    
    public int getChannel() {
        return channel;
    }

    public int getHighPass() {
        return highPass;
    }

}
