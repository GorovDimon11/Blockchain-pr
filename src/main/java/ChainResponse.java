import java.util.ArrayList;
import java.util.List;

public class ChainResponse {
    List<Block> GDRchain = new ArrayList<>();
    int GDRlength;

    public ChainResponse(List<Block> GDRchain, int GDRlength) {
        this.GDRchain = GDRchain;
        this.GDRlength = GDRlength;
    }
}
