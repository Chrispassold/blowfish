package src.assimetrica;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

abstract class BaseCryptDecrypt {
    final Chave chave;
    final KeyFactory kf;
    final Cipher cipher;
    final BASE64Encoder base64Encoder = new BASE64Encoder();
    final BASE64Decoder base64Decoder = new BASE64Decoder();

    BaseCryptDecrypt(Chave chave) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.chave = chave;
        this.kf = KeyFactory.getInstance("RSA");
        this.cipher = Cipher.getInstance("RSA");
    }

    void toFile(byte[] bytes, File fileout) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileout.getAbsoluteFile())) {
            fos.write(bytes);
        }
    }

    void toConsole(byte[] bytes) {
        System.out.println();
        System.out.println("================ TEXTO ================");
        System.out.println(new String(bytes));
        System.out.println("=======================================");
        System.out.println();
    }

    public abstract void process(File filein, File fileout) throws Exception;

    public abstract void process(String string) throws Exception;

}
