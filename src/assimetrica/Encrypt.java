package src.assimetrica;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

public class Encrypt extends BaseCryptDecrypt {

    private Encrypt(Chave chave) throws NoSuchPaddingException, NoSuchAlgorithmException {
        super(chave);
    }

    static BaseCryptDecrypt from(Chave chave) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return new Encrypt(chave);
    }

    public void process(File filein, File fileout) throws Exception {
        final byte[] bytes = Files.readAllBytes(filein.toPath());
        byte[] signed = process(bytes);
        toFile(signed, fileout);
    }

    private byte[] process(byte[] bytes) throws Exception {
        RSAPrivateKeySpec spec = chave.getPrivateKeySpec();
        final PrivateKey key = kf.generatePrivate(spec);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }
}
