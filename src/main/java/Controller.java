import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import spark.Spark;

import java.util.List;
import java.util.UUID;

import static spark.Spark.*;

public class Controller {
    public static void main(String[] args) {
        Spark.port(4567);
        Blockchain blockchain = new Blockchain();
        Gson gson = new Gson();

        get("/mine", (req, res) -> {
            Block lastBlock = blockchain.GDRlastBlock();

            String lastProof = String.valueOf(lastBlock.getGDRproof());
            int proofOfWork = blockchain.GDRproofOfWork(lastProof);

            blockchain.GDRnewTransaction("0",
                    UUID.randomUUID().toString().replace("-", ""), 11);
            String lastHash = Blockchain.GDR_hash(lastBlock);
            Block newBlock = blockchain.GDRnewBlock(blockchain.GDRproofOfWork(lastHash), lastHash);
            //Block newBlock = blockchain.GDRnewBlock(proofOfWork, lastHash);
            // створюємо рядок, що є відображенням нового блока
            String json = gson.toJson(newBlock);
            // ставимо статус відповіді 200
            res.status(200);
            // відправляюємо відомості про новий блок
            return json;
        });

        post("/transactions/new", (req, res) -> {
            try {
                Transaction transaction = gson.fromJson(req.body(),
                        Transaction.class);
                // додаємо нову транзакцію
                int index = blockchain.GDRnewTransaction(transaction.getGDRsender(), transaction.getGDRrecipient(), transaction.getGDRamount());
                // якщо помилок не відбулося, то відправляємо відповідь
                res.status(201);
                return "Transaction will be added to Block " + index;
            } catch (JsonSyntaxException e) {
                // якщо запит має неправильну структуру - повертаємо по-милку
                res.status(400);
                return "Invalid JSON";
            }

        });

        get("/chain", (req, res) -> {
            return gson.toJson(blockchain.GDRgetChain());
        });

        post("/nodes/register", (req, res) -> {
            try {
                List<String> nodes = gson.fromJson(req.body(),
                        NodesResponse.class).nodes;
                for (String node : nodes) {
                    blockchain.GDRregisterNode(node);
                }
                return gson.toJson(blockchain.getGDRnodes());
            } catch (Exception e) {
                res.status(400);
                return "Incorrect host address";
            }
        });

        get("/nodes/resolve", (req, res) -> {
            if (blockchain.GDRresolveConflicts()) {
                return gson.toJson(new
                        ChainResponse(blockchain.GDRgetChain(), blockchain.GDRgetChain().size()));
            } else {
                return gson.toJson(new
                        ChainResponse(blockchain.GDRgetChain(), blockchain.GDRgetChain().size()));
            }

        });


    }

}
