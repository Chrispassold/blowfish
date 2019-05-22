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
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        final Crypth crypth = new Crypth();
        final Questoes questoes = new Questoes(crypth);

        questoes.exercicio1();

        String texto = ask("Informe o texto para encriptar");
        questoes.exercicio2(texto);

        questoes.exercicio3();

    }

    // Method which write the bytes into a file
    public static void writeBytes(byte[] bytes, File fileout) throws IOException {
        if (fileout.exists())
            fileout.delete();

        System.out.println("Escrevendo bytes para " + fileout.getName());
        try (OutputStream os = new FileOutputStream(fileout)) {
            os.write(bytes);
        }
    }

    public static byte[] readBytes(File fileout) throws IOException {
        System.out.println("Lendo bytes de " + fileout.getName());
        return Files.readAllBytes(fileout.toPath());
    }

    private static String ask(String message) {
        System.out.println();
        System.out.println();
        System.out.println(message);

        return scanner.nextLine();
    }
}