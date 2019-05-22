package src.assimetrica2;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class CrypthMain {
    private final static File fileToCrypt = new File("./tocrypt.txt");
    private final static File fileOutputCrypt = new File("./encrypt.enc");
    private final static File fileOutputDecrypt = new File("./decrypt.dec");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        final Crypth crypth = new Crypth();
        final Questoes questoes = new Questoes(crypth);

        String option;

        questoes.exercicio1();

        do {
            System.out.println();
            System.out.println();
            System.out.println("1 - Encriptar arquivo");
            System.out.println("2 - Decriptar arquivo");
            System.out.println("0 - Sair");

            option = scanner.nextLine();

            if (option.equals("1")) {
                String texto = ask("Informe o texto para encriptar");
                questoes.exercicio2(texto);
            }

            if (option.equals("2")) {
                questoes.exercicio3();
            }

            if (option.equals("0")) {
                System.out.println("XAU!!");
            }

        } while (!option.equals("0"));

    }

    // Method which write the bytes into a file
    public static void writeBytes(byte[] bytes, File fileout) throws IOException {
        if(fileout.exists())
            fileout.delete();

        try (OutputStream os = new FileOutputStream(fileout)) {
            os.write(bytes);
        }
    }

    public static byte[] readBytes(File fileout) throws IOException {
        return Files.readAllBytes(fileout.toPath());
    }

    private static String ask(String message) {
        System.out.println();
        System.out.println();
        System.out.println(message);

        return scanner.nextLine();
    }
}