public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Block soo_block = blockchain.GDRlastBlock();
        System.out.println(soo_block.getGDRpreviousHash() + " " + soo_block.getGDRproof() + " " + Blockchain.GDR_hash(soo_block));
        Block soo_block2 = blockchain.GDRnewBlock(blockchain.GDRproofOfWork(Blockchain.GDR_hash(soo_block)), Blockchain.GDR_hash(soo_block));
        System.out.println(soo_block2.getGDRpreviousHash() + " " + Blockchain.GDR_hash(soo_block2) + " " + soo_block2.getGDRproof());
    }
}
