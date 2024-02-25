package nl.sikken.bertrik.anabat;

public enum EChannel {
    LEFT(0, "Left"),
    RIGHT(1, "Right");
    
    private final int index;
    private final String desc;

    private EChannel(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }
    
    public int getIndex() {
        return index;
    }
    
    @Override
    public String toString() {
        return desc;
    }
}
