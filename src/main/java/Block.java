import java.util.List;

public class Block {

    private int GDRindex;
    private long GDRtimestamp;
    private List<Transaction> GDRtransactions;
    private int GDRproof;
    private String GDRpreviousHash;

    public Block(int GDRindex, int GDRproof, String GDRpreviousHash,
                 List<Transaction> GDRtransactions) {
        this.GDRindex = GDRindex;
        this.GDRproof = GDRproof;
        this.GDRpreviousHash = GDRpreviousHash;
        this.GDRtransactions = GDRtransactions;
        this.GDRtimestamp = System.currentTimeMillis();
    }

    public int getGDRindex() {
        return GDRindex;
    }

    public long getGDRtimestamp() {
        return GDRtimestamp;
    }

    public List<Transaction> getGDRtransactions() {
        return GDRtransactions;
    }

    public int getGDRproof() {
        return GDRproof;
    }

    public String getGDRpreviousHash() {
        return GDRpreviousHash;
    }
}