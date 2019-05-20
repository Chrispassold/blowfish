package src.assimetrica;

import java.io.File;

public class Main {

    private final static File fileToCrypt = new File("src/assimetrica/tocrypt.txt");
    private final static File fileOutputCrypt = new File("./encrypt.enc");
    private final static File fileOutputDecrypt = new File("./decrypt.dec");

    public static void main(String[] args) throws Exception {
        final Chave chave = Chave.loadOrGenerate();

        Encrypt.from(chave).process(fileToCrypt, fileOutputCrypt);
        Decrypt.from(chave).process(fileOutputCrypt, fileOutputDecrypt);
    }
}
