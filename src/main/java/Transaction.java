

public class Transaction {

    private String GDRsender;
    private String GDRrecipient;
    private int GDRamount;


    public Transaction(String GDRsender, String GDRrecipient, int GDRamount) {
        this.GDRsender = GDRsender;
        this.GDRrecipient = GDRrecipient;
        this.GDRamount = GDRamount;
    }

    public String getGDRsender() {
        return GDRsender;
    }

    public String getGDRrecipient() {
        return GDRrecipient;
    }

    public int getGDRamount() {
        return GDRamount;
    }


}
