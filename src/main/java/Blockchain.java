
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Blockchain {
    private List<Block> GDRchain = new ArrayList<>();
    private List<Transaction> GDRcurrentTransactions = new ArrayList<>();

    public Blockchain() {
        GDRnewBlock(11052001, "Gorovenko");
    }

    /**
     * Проста перевірка алгоритму: Пошук числа p`, так як hash(pp`)
     * містить 4
     * заголовних нуля, де p — попередній p є попереднім доказом, а p`
     * — 5новим
     *
     * @parama lastProofOfWork
     * @return int
     */
    public int GDRproofOfWork(String prevHash) {
        int GDRproof = 0;
        while (!GDRcreateHash(this.GDRchain.size(), GDRproof, prevHash).endsWith("05")) {
            GDRproof++;
        }
        return GDRproof;
    }

    /**
     * Підтвердження доказу: Чи містить hash(lastProof, proof)
     * заголовних нуля
     *
     * @param lastProof
     * @param proof
     * @return
     *
     */
    /*private boolean GDRisProofValid(int lastProof, int proof) {

        String GDRguessString = Integer.toString(lastProof) +
                Integer.toString(proof);
        String GDRguessHash = Hashing.sha256().hashString(GDRguessString,
                StandardCharsets.UTF_8).toString();
        return GDRguessHash.endsWith("05");
    }*/

    /**
     *
     * @param proof Докази проведенної роботи
     * @param previousHash Хеш попереднього блока
     * @return Новий блок
     * 6
     */
    public Block GDRnewBlock(int proof, String previousHash) {
        // створюмо копію списка
        List<Transaction> GDRtransactions = this.GDRcurrentTransactions.
                stream().collect(Collectors.toList());
        // створюємо новий об'єкт блока
        Block GDRnewBlock = new Block(this.GDRchain.size(), proof,
                previousHash, GDRtransactions);
        // очищаємо список транзакцій
        this.GDRcurrentTransactions.clear();
        // додаємо новий блок у цепочку
        this.GDRchain.add(GDRnewBlock);
        // повертаємо новий блок
        return GDRnewBlock;
    }

    /**
     *
     * Направляє нову транзакцію в наступний блок
     *
     *
     * @param sender Адреса відправника
     * @param recipient Адреса отримувача
     * @param amount Сума
     * @return Індекса блока, що буде зберігати цю транзакцію
     *
     */

    public int GDRnewTransaction(String sender, String recipient, int amount) {
        this.GDRcurrentTransactions.add(new Transaction(sender, recipient,
                amount));
        return this.GDRchain.size();
    }

    /**
     *
     * @parama block Блок
     * @return Хеш блока
     *
     */
    public static String GDRcreateHash(int index, int proof, String prevHash) {
        StringBuilder GDRhashingInputBuilder = new StringBuilder();

        GDRhashingInputBuilder.append(index)
                .append(proof)
                .append(prevHash);

        String GDRhashingInput = GDRhashingInputBuilder.toString();

        return sha256(GDRhashingInput);
    }

    public static String GDR_hash(Block block) {
        return GDRcreateHash(block.getGDRindex(),
                block.getGDRproof(), block.getGDRpreviousHash());

    }

    public Block GDRlastBlock() {

        /*if (this.GDRchain.size() > 0)
            return this.GDRchain.get(this.GDRchain.size() - 1);
        else
            return null;*/

        return this.GDRchain.size() > 0 ? this.GDRchain.get(this.GDRchain.size() -
                1) : null;
    }

    public List<Block> GDRgetChain() {
        return this.GDRchain;
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
