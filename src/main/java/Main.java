public class Main {
    public static void main(String[] args) {
        Blockchain GDRblockchain = new Blockchain();
        Block GDRcurrentBlock = GDRblockchain.GDRlastBlock();

        System.out.println(GDRcurrentBlock.getGDRindex()+"\n"+GDRcurrentBlock.getGDRpreviousHash()+'\n'
        +GDRcurrentBlock.getGDRproof());
    }
}
