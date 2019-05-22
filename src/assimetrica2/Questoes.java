package src.assimetrica2;

import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

public class Questoes {
    private final static File TEXTOENC = new File("./texto.enc");
    private final static File AESENC = new File("./aes.enc");
    private final Crypth crypth;

    private final static BASE64Encoder encoder = new BASE64Encoder();

    public Questoes(Crypth crypth) {
        this.crypth = crypth;
    }

    private void print(String print){
        System.out.println(print);
    }

    public void exercicio1() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        final RSAPublicKey aPublic = Crypth.getPUBLIC();
        final RSAPrivateKey aPrivate = Crypth.getPRIVATE();
        System.out.println();
        System.out.println();
        print("=========================================");
        print("===============EXERCICIO 1===============");

        print(String.format("Public key modulo: %s", aPublic.getModulus()));
        print(String.format("Public key expoente: %s", aPublic.getPublicExponent()));

        print(String.format("Private key modulo: %s", aPrivate.getModulus()));
        print(String.format("Private key expoente: %s", aPrivate.getPrivateExponent()));
    }

    public void exercicio2(String plaintext) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeySpecException {
        System.out.println();
        System.out.println();
        print("=========================================");
        print("===============EXERCICIO 2===============");
        final byte[] bytes = crypth.encryptAES(plaintext);
        CrypthMain.writeBytes(bytes, TEXTOENC);
        print(String.format("PLAIN TEXT %s", plaintext));
        print(String.format("PLAIN TEXT CIFRADA RSA - %s", encoder.encode(bytes)));
        final byte[] bytes1 = crypth.encryptAESKeyWithRSAKey();
        CrypthMain.writeBytes(bytes1, AESENC);
    }

    public void exercicio3() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        System.out.println();
        System.out.println();
        print("=========================================");
        print("===============EXERCICIO 3===============");
        final byte[] bytes = CrypthMain.readBytes(AESENC);
        final byte[] aeskey = crypth.decryptAESKeyWithRSAKey(bytes);
        final byte[] file = CrypthMain.readBytes(TEXTOENC);
        final String s = crypth.decryptMessage(file, aeskey);
        print(s);
    }
}
