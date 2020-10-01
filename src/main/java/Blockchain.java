
import org.sparkproject.guava.hash.Hashing;

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
     * @param lastProofOfWork
     * @return int
     */
    public int GDRproofOfWork(int lastProofOfWork) {
        int GDRproof = 0;
        while (!GDRisProofValid(lastProofOfWork, GDRproof)) {
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
    private boolean GDRisProofValid(int lastProof, int proof) {

        String GDRguessString = Integer.toString(lastProof) +
                Integer.toString(proof);
        String GDRguessHash = Hashing.sha256().hashString(GDRguessString,
                StandardCharsets.UTF_8).toString();
        return GDRguessHash.endsWith("05");
    }


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
        Block GDRnewBlock = new Block(this.GDRchain.size(), proof, previousHash,
                GDRtransactions);

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

    public int GDRnewTransaction(String sender, String recipient, int
            amount) {
        this.GDRcurrentTransactions.add(new Transaction(sender, recipient,
                amount));
        return this.GDRchain.size();
    }

    /**
     *
     * @param block Блок
     * @return Хеш блока
     *
     */
    public static String GDRhash(Block block) {
        StringBuilder GDRhashingInputBuilder = new StringBuilder();

        // додаємо параметри блока у змінну в певному незмінному по-рядку
        GDRhashingInputBuilder.append(block.getGDRindex())
                .append(block.getGDRtimestamp()).append(block.getGDRproof())
                .append(block.getGDRpreviousHash());

        String GDRhashingInput = GDRhashingInputBuilder.toString();
        // генеруємо хеш блока на основі її полів за допомогою змінної
        return Hashing.sha256().hashString(GDRhashingInput, StandardCharsets.UTF_8).toString();
    }

    public Block GDRlastBlock() {

        return this.GDRchain.size() > 0 ? this.GDRchain.get(this.GDRchain.size() -
                1) : null;
    }

}
