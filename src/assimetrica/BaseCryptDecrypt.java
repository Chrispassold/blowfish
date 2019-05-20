package src.assimetrica;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

abstract class BaseCryptDecrypt {
    protected final Chave chave;
    protected final KeyFactory kf;
    protected final Cipher cipher;

    BaseCryptDecrypt(Chave chave) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.chave = chave;
        this.kf = KeyFactory.getInstance("RSA");
        this.cipher = Cipher.getInstance("RSA");
    }

    protected void toFile(byte[] bytes, File fileout) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileout.getAbsoluteFile())) {
            fos.write(bytes);
        }
    }

    public abstract void process(File filein, File fileout) throws Exception;
}
