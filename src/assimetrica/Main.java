package src.assimetrica;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.util.Scanner;

public class Main {

    private final static File fileToCrypt = new File("src/assimetrica/tocrypt.txt");
    private final static File fileOutputCrypt = new File("./encrypt.enc");
    private final static File fileOutputDecrypt = new File("./decrypt.dec");
    private final static BASE64Decoder base64Decoder = new BASE64Decoder();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        final Chave chave = Chave.loadOrGenerate();

        String option;

        do {
            System.out.println("1 - Encriptar arquivo");
            System.out.println("2 - Decriptar arquivo");
            System.out.println("3 - Exibir chave");
            System.out.println("0 - Sair");

            option = scanner.nextLine();

            if (option.equals("1")) {
                String texto = ask("Informe o texto para encriptar");
                Encrypt.from(chave).process(texto);
            }

            if (option.equals("2")) {
                String texto = ask("Informe o texto para decriptar");
                Decrypt.from(chave).process(texto);
            }

            if (option.equals("3")) {
                System.out.println(chave.toString());
            }

            if (option.equals("0")) {
                System.out.println("XAU!!");
            }

        } while (!option.equals("0"));

//        Encrypt.from(chave).process(fileToCrypt, fileOutputCrypt);
//        Encrypt.from(chave).process(fileToCrypt, fileOutputCrypt);
//        Decrypt.from(chave).process(fileOutputCrypt, fileOutputDecrypt);
    }

    private static String ask(String message) {
        System.out.println(message);

        return scanner.nextLine();
    }
}
